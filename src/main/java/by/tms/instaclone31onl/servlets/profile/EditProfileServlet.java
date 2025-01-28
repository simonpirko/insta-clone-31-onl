package by.tms.instaclone31onl.servlets.profile;

import by.tms.instaclone31onl.core.constants.AttributeConstants;
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
import java.util.HashMap;
import java.util.List;

import static by.tms.instaclone31onl.core.constants.AppConstants.IMAGES_PROFILE_DESTINATION_FORMAT_PATH;

@WebServlet(ServletConstants.EDIT_PROFILE_SERVLET)
public class EditProfileServlet extends BaseApiServlet {

    private final ServiceFactory serviceFactory;

    public EditProfileServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/edit.jsp").forward(request, response);
    }

    private User getUserUpdateRequest(HttpServletRequest request) throws IOException {
        HashMap<String, Object> multiPartContent = getMultiPartContent(request, IMAGES_PROFILE_DESTINATION_FORMAT_PATH.formatted(currentUser.getLogin()));
        String description = null;
        String nickname = null;
        List<String> photos = null;
        if (multiPartContent.containsKey("description")) {
            description = (String) multiPartContent.get("description");
        }
        if (multiPartContent.containsKey("nickname")) {
            nickname = (String) multiPartContent.get("nickname");
        }
        if (multiPartContent.containsKey("namePhoto")) {
            photos = (List<String>) multiPartContent.get("namePhoto");
        }
        return new User(currentUser.getId(), currentUser.getLogin(), currentUser.getHash(), nickname, currentUser.getFriendIds(), currentUser.getBlackList(), photos, description);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = serviceFactory.getUserService();
        User user = getUserUpdateRequest(request);
        userService.editUser(user);
        request.getSession().setAttribute(AttributeConstants.CURRENT_USER, user);
        response.sendRedirect(request.getContextPath() + ServletConstants.PROFILE_SERVLET);
    }
}
