package by.tms.instaclone31onl.servlets.profile;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(ServletConstants.LOGOUT_SERVLET)
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate(); // завершение сеанса
        response.sendRedirect(ServletConstants.LOGIN_SERVLET);  // переход на страницу входа
    }
}

