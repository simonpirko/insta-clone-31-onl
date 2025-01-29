package by.tms.instaclone31onl.servlets.profile;

import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.tms.instaclone31onl.core.constants.ServletConstants;

import java.io.IOException;
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
        if (str == null || str.equals(currentUser.getId().toString())) {
            request.setAttribute("isOwner", true);
            request.setAttribute("profileUser", currentUser);
        }
        else {
            UUID uuid = UUID.fromString(str);
            UserService service = serviceFactory.getUserService();
            User user = service.getUserById(uuid);
            request.setAttribute("isOwner", false);
            request.setAttribute("profileUser", user);
        }
        request.getRequestDispatcher("/pages/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/profile.jsp").forward(request, response);
    }
}
