package by.tms.instaclone31onl.servlets.profile;

import by.tms.instaclone31onl.core.interfaces.factories.RepositoryFactory;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.factories.InstaRepositoryFactory;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.repositories.InstaUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.tms.instaclone31onl.core.constants.ServletConstants;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(ServletConstants.PROFILE_SERVLET)
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //tmp
        RepositoryFactory factory = InstaRepositoryFactory.getInstance();
        UserRepository userRepository = factory.getUserRepository();
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", userRepository.getUserByLogin("login3"));
        //tmp

        request.getRequestDispatcher("/pages/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
