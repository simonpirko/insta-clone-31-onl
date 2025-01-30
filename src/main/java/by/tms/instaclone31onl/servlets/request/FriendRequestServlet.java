package by.tms.instaclone31onl.servlets.request;

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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet(ServletConstants.FRIEND_REQUEST_SERVLET)
public class FriendRequestServlet extends BaseApiServlet {

    private final ServiceFactory serviceFactory;

    public FriendRequestServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID requesterId = UUID.fromString(req.getParameter("requesterId"));
        boolean rejected = Boolean.parseBoolean(Optional.ofNullable(req.getParameter("rejected")).orElse("false"));
        FriendRequestService friendRequestService = serviceFactory.getFriendRequestService();
        if(rejected) {
            friendRequestService.removeRequest(requesterId,currentUser.getId());
        }else{
            friendRequestService.acceptRequest(requesterId,currentUser.getId());
            currentUser.addFriend(requesterId);
        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }

}