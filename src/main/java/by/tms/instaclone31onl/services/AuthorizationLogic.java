package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthorizationLogic {
    private ServiceFactory serviceFactory;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public AuthorizationLogic(ServiceFactory serviceFactory, HttpServletRequest request, HttpServletResponse response) {
        this.serviceFactory = serviceFactory;
        this.request = request;
        this.response = response;
    }

    public void checkingForCompletion() throws IOException, ServletException {
        if (StringUtils.isEmpty(request.getParameter("aPass")) ||
                StringUtils.isEmpty(request.getParameter("aPassCopy")) ||
                StringUtils.isEmpty(request.getParameter("aName"))) {
            printMessage("Заполните все поля");
        } else {
            if (serviceFactory.getUserService().checkLogin(request.getParameter("aName"))) {
                printMessage("Такое имя пользователя уже существует");
            } else {
                if (serviceFactory.getUserService().checkRegistr(request.getParameter("aName"), request.getParameter("aPass"))) {
                    request.getSession().setAttribute("user", request.getParameter("aName"));
                    response.sendRedirect("pages/welcome.jsp?name=" + request.getParameter("aName"));
                } else {
                    printMessage("Пароли не совпадают");
                }
            }
        }
    }

    private void printMessage(String message) throws IOException, ServletException {
        response.getWriter().println("<div align='center' style='color: red'>" + message + "</div>");
        request.getRequestDispatcher("pages/authorization.jsp").include(request, response);
    }
}