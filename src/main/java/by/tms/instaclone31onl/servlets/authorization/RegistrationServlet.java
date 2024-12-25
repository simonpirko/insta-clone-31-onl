package by.tms.instaclone31onl.servlets.authorization;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.services.RegistrationLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(ServletConstants.AUTHORIZATION_SERVLET)
public class RegistrationServlet extends HttpServlet {
    private final ServiceFactory serviceFactory;

    public RegistrationServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");

        RegistrationLogic registrationLogic = new RegistrationLogic(serviceFactory, request, response);
        registrationLogic.checkingForCompletion();
    }
}