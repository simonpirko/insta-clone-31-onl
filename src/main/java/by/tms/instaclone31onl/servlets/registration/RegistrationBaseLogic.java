package by.tms.instaclone31onl.servlets.registration;

import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.utils.StringUtils;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseLogic;
import by.tms.instaclone31onl.servlets.login.LoginBaseLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegistrationBaseLogic extends BaseLogic {

    public RegistrationBaseLogic(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void checkingForCompletion() throws IOException, ServletException {
        ServiceFactory serviceFactory = InstaServiceFactory.getInstance();
        if (StringUtils.isEmpty(getRequest().getParameter("aPass")) ||
                StringUtils.isEmpty(getRequest().getParameter("aPassCopy")) ||
                StringUtils.isEmpty(getRequest().getParameter("aName"))) {
            printMessage("Заполните все поля");
        } else {
            if (serviceFactory.getUserService().checkLogin(getRequest().getParameter("aName"))) {
                printMessage("Такое имя пользователя уже существует");
            } else {
                if (serviceFactory.getUserService().checkRegistration(getRequest().getParameter("aName"), getRequest().getParameter("aPass"))) {
                    new LoginBaseLogic(getRequest(), getResponse()).goToProfilePage(getRequest().getParameter("aName"));
                } else {
                    printMessage("Пароли не совпадают");
                }
            }
        }
    }

    private void printMessage(String message) throws IOException, ServletException {
        getResponse().getWriter().println("<div align='center' style='color: red'>" + message + "</div>");
        getRequest().getRequestDispatcher("pages/registration.jsp").include(getRequest(), getResponse());
    }
}