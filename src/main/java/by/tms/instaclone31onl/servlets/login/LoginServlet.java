package by.tms.instaclone31onl.servlets.login;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(ServletConstants.LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {

    private final ServiceFactory serviceFactory;

    public LoginServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String login = request.getParameter("usName");
        String password = request.getParameter("usPass");

        User user = serviceFactory.getUserService().getUser(login, password);

        if (Objects.nonNull(user)) {
            request.getSession().setAttribute("currentUser", user);
            response.sendRedirect(ServletConstants.PROFILE_SERVLET);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("pages/login.jsp");
            response.getWriter().println("<div align='center' style='color: red'>Неправильный логин или пароль</div>");
            rd.include(request, response);
        }
    }
}