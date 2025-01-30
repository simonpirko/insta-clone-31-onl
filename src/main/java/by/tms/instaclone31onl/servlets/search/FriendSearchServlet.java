package by.tms.instaclone31onl.servlets.search;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
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


@WebServlet(ServletConstants.FRIEND_SEARCH_SERVLET)
public class FriendSearchServlet extends BaseApiServlet {

    private final ServiceFactory serviceFactory;

    public FriendSearchServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        Optional optionalUUID = Optional.ofNullable(request.getParameter("userId"));
        List<UUID> friendIds = null;

        UserService userService = serviceFactory.getUserService();

        if(optionalUUID.isPresent()) {
            UUID uuid = UUID.fromString(optionalUUID.get().toString());
            User user = userService.getUserById(uuid);
            friendIds = user.getFriendIds();
        }else{
            friendIds = currentUser.getFriendIds();
        }
        List<User> friends = userService.searchIn(friendIds, text);
        request.setAttribute("users", friends);
        request.getRequestDispatcher("/pages/friends.jsp").forward(request, response);
    }
}