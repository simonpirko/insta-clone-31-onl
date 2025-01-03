package by.tms.instaclone31onl.servlets.registration;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.filters.RegistrationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        String login = request.getParameter("aName");
        String password = request.getParameter("aPass");
        String message = RegistrationFilter.checkUserRegistration(
                login,
                password,
                request.getParameter("aPassCopy"));

        if(Objects.nonNull(message)){
            response.getWriter().println("<div align='center' style='color: red'>" + message + "</div>");
            request.getRequestDispatcher("pages/registration.jsp").include(request, response);
        }else {
            request.getSession().setAttribute("currentUser", userService.getUser(login, password));
            response.sendRedirect(ServletConstants.PROFILE_SERVLET);
        }
    }
}