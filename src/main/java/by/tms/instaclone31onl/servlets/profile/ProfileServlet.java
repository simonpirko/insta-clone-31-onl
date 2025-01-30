package by.tms.instaclone31onl.servlets.profile;

import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.FriendRequestService;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.models.dtos.UserShortDto;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.tms.instaclone31onl.core.constants.ServletConstants;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@WebServlet(ServletConstants.PROFILE_SERVLET)
public class ProfileServlet extends BaseApiServlet {
    private final ServiceFactory serviceFactory;

    public ProfileServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("id");
        FriendRequestService friendRequestService = serviceFactory.getFriendRequestService();
        UserService userService = serviceFactory.getUserService();

        List<UserShortDto> myRequests = friendRequestService.getRequesterRequests(currentUser.getId());
        List<UserShortDto> requestToMy = friendRequestService.getReceiverRequests(currentUser.getId());

        boolean hasRequestToFriendship = false;
        boolean underСonsideration = false;
        User profileUser;
        if (str == null || UUID.fromString(str).equals(currentUser.getId()) ) {
            profileUser = currentUser;
            request.setAttribute("myRequests", myRequests);
            request.setAttribute("requestsToMy", requestToMy);
        }
        else {
            UUID uuid = UUID.fromString(str);
            profileUser = userService.getUserById(uuid);
            boolean inMyRequest = myRequests.stream().anyMatch(r->r.id().equals(profileUser.getId()));

            //User myCurrentProfile = userService.getUserById(currentUser.getId());
            boolean isMyFriend = currentUser.getFriendIds().contains(profileUser.getId());
            boolean inRequestToMy = requestToMy.stream().anyMatch(r->r.id().equals(profileUser.getId()));
            underСonsideration = inMyRequest || inRequestToMy;
            hasRequestToFriendship = isMyFriend || underСonsideration ;
        }
        request.setAttribute("profileUser", profileUser);
        request.setAttribute("hasRequestToFriendship", hasRequestToFriendship);
        request.setAttribute("underСonsideration", underСonsideration);
        request.setAttribute("isOwner", str == null || UUID.fromString(str).equals(currentUser.getId()));
        request.getRequestDispatcher("/pages/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/profile.jsp").forward(request, response);
    }
}
