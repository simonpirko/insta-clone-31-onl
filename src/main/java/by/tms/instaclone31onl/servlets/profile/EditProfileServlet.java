package by.tms.instaclone31onl.servlets.profile;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(ServletConstants.EDIT_PROFILE_SERVLET)
public class EditProfileServlet extends BaseApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/profile.jsp").forward(request, response);

        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String description = request.getParameter("description");
    }
}
