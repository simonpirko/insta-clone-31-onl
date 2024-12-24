package by.tms.instaclone31onl.servlets.login;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String user = request.getParameter("usName");
        String password = request.getParameter("usPass");
      
        UserService userService = serviceFactory.getUserService();
      
        if(userService.checkUser(user, password)) {
            session.setAttribute("user", user);
            response.sendRedirect("pages/welcome.jsp?name=" + user);
            return;
        }
      
        RequestDispatcher rd = request.getRequestDispatcher("pages/login.jsp");
        out.println("<div align='center' style='color: red'>Неправильный логин или пароль</div>");
        rd.include(request, response);
    }
}