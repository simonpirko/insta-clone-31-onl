package by.tms.instaclone31onl.servlets.registration;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.factories.InstaRepositoryFactory;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Objects;

@WebServlet(ServletConstants.REGISTRATION_SERVLET)
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");

        UserService userService = InstaServiceFactory.getInstance().getUserService();
        String message = userService.checkUserRegistration(
                request.getParameter("aName"),
                request.getParameter("aPass"),
                request.getParameter("aPassCopy"));

        if(Objects.nonNull(message)){
            response.getWriter().println("<div align='center' style='color: red'>" + message + "</div>");
            request.getRequestDispatcher("pages/registration.jsp").include(request, response);
        }else {
            UserRepository userRepository = InstaRepositoryFactory.getInstance().getUserRepository();
            request.getSession().setAttribute("currentUser", userRepository.getUserByLogin(request.getParameter("aName")));
            response.sendRedirect(ServletConstants.PROFILE_SERVLET);
        }
    }
}