package by.tms.instaclone31onl.servlets.login;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.factories.InstaRepositoryFactory;
import by.tms.instaclone31onl.servlets.base.Logic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginLogic  extends Logic {

    public LoginLogic(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void goToProfilePage(String login) throws IOException {
        HttpSession session = getRequest().getSession();
        UserRepository userRepository = InstaRepositoryFactory.getInstance().getUserRepository();
        session.setAttribute("currentUser", userRepository.getUserByLogin(login));
        getResponse().sendRedirect(ServletConstants.PROFILE_SERVLET);
    }
}
