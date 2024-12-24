package by.tms.instaclone31onl.servlets.authorization;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import com.opencsv.CSVWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.UUID;

@WebServlet(ServletConstants.AUTHORIZATION_SERVLET)
public class AuthorizationServlet extends HttpServlet {
    private final ServiceFactory serviceFactory;

    public AuthorizationServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/authorization.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        checkingForCompletion(request, response, out);
        out.close();
    }

    private void recordingData(HttpServletRequest request, HttpSession session, HttpServletResponse response, PrintWriter out) throws IOException, ServletException {
        UserService userService = serviceFactory.getUserService();
        if (userService.checkRegistr(request.getParameter("aName"), request.getParameter("aPass"))) {
            session.setAttribute("user", request.getParameter("aName"));
            response.sendRedirect("pages/welcome.jsp?name=" + request.getParameter("aName"));
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("pages/authorization.jsp");
            out.println("<div align='center' style='color: red'>Пароли не совпадают</div>");
            rd.include(request, response);
        }
    }

    public void checkingForCompletion(HttpServletRequest request, HttpServletResponse response,
                                      PrintWriter out) throws IOException, ServletException {
        if (request.getParameter("aPass").isEmpty() || request.getParameter("aPassCopy").isEmpty() ||
                request.getParameter("aName").isEmpty()) {
            RequestDispatcher rd = request.getRequestDispatcher("pages/authorization.jsp");
            out.println("<div align='center' style='color: red'>Заполните все поля</div>");
            rd.include(request, response);
        } else {
            checkUser(request, response, out);
        }
    }

    private void checkUser(HttpServletRequest request, HttpServletResponse response,
                           PrintWriter out)
            throws IOException, ServletException {
        UserService userService = serviceFactory.getUserService();
        if (userService.checkLogin(request.getParameter("aName"))) {
            RequestDispatcher rd = request.getRequestDispatcher("pages/authorization.jsp");
            out.println("<div align='center' style='color: red'>Такое имя пользователя уже существует</div>");
            rd.include(request, response);

        } else {
            recordingData(request, request.getSession(), response, out);
        }
    }
}