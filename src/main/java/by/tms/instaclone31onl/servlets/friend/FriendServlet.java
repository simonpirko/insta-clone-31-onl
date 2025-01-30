package by.tms.instaclone31onl.servlets.friend;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.FriendRequestService;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@WebServlet(ServletConstants.FRIENDS_SERVLET)
public class FriendServlet extends BaseApiServlet {

    private final ServiceFactory serviceFactory;

    public FriendServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID userId = UUID.fromString(req.getParameter("userId"));
        FriendRequestService friendRequestService = serviceFactory.getFriendRequestService();
        friendRequestService.addRequest(currentUser.getId(), userId);
        resp.sendRedirect(req.getContextPath() + "/profile?id=" + userId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Optional uuidOptional = Optional.ofNullable(request.getParameter("userId"));

        UserService userService = serviceFactory.getUserService();
        User user = userService.getUserById(uuidOptional.isPresent()? UUID.fromString(uuidOptional.get().toString()) :currentUser.getId() );
        List<UUID> friendIds = user.getFriendIds();
        List<User> friends = userService.searchIn(friendIds, null);
        request.setAttribute("users", friends);
        request.getRequestDispatcher("/pages/friends.jsp").forward(request, response);
    }
}