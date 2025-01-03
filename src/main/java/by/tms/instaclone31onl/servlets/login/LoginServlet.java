package by.tms.instaclone31onl.servlets.login;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.factories.InstaRepositoryFactory;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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

        if (serviceFactory.getUserService().checkUser(login, password)) {
            UserRepository userRepository = InstaRepositoryFactory.getInstance().getUserRepository();
            request.getSession().setAttribute("currentUser", userRepository.getUserByLogin(request.getParameter("aName")));
            response.sendRedirect(ServletConstants.PROFILE_SERVLET);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("pages/login.jsp");
            response.getWriter().println("<div align='center' style='color: red'>Неправильный логин или пароль</div>");
            rd.include(request, response);
        }
    }
}