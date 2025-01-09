package by.tms.instaclone31onl.filters;

import by.tms.instaclone31onl.core.constants.AttributeConstants;
import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.utils.StringUtils;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(value=ServletConstants.REGISTRATION_SERVLET)
public class RegistrationFilter extends HttpFilter {

    private final ServiceFactory serviceFactory;
    public RegistrationFilter() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if(!req.getMethod().equals("POST")) {
            chain.doFilter(req, res);
            return;
        }
        UserService userService = serviceFactory.getUserService();
        String login = req.getParameter("aName");
        String password = req.getParameter("aPass");
        String confirmPassword = req.getParameter("aPassCopy");
        List<String> errors = new ArrayList<>();

        if (StringUtils.isEmpty(login) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(confirmPassword )) {
            errors.add("Заполните все поля");
        }
        if(StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(confirmPassword) && !password.equals(confirmPassword)){
            errors.add("Пароли не совпадают");
        }

        if(StringUtils.isNotEmpty(login) && !userService.checkLogin(login)){
            errors.add("Такой пользователь уже существует");
        }

        if(!errors.isEmpty()){
            req.setAttribute(AttributeConstants.ERRORS, errors);
            req.getRequestDispatcher("pages/registration.jsp").include(req, res);
        }
        else{
            chain.doFilter(req, res);
        }


//        else {
//            if (pass.equals(passCopy)) {
//                if (userService.checkLogin(login)) {
//                    userService.insertUser(login, pass);
//                    return null;
//                }
//                return "Такой пользователь уже существует";
//            }
//            return "Пароли не совпадают";
//        }

//        String message = RegistrationFilter.checkUserRegistration(
//                login,
//                password,
//                request.getParameter("aPassCopy"));
    }



//
//    public static String checkUserRegistration(String login, String pass, String passCopy) {
//        UserService userService = InstaServiceFactory.getInstance().getUserService();
//        if (StringUtils.isEmpty(login) ||
//                StringUtils.isEmpty(pass) ||
//                StringUtils.isEmpty(passCopy)) {
//            return "Заполните все поля";
//        } else {
//            if (pass.equals(passCopy)) {
//                if (userService.checkLogin(login)) {
//                    userService.insertUser(login, pass);
//                    return null;
//                }
//                return "Такой пользователь уже существует";
//            }
//            return "Пароли не совпадают";
//        }
//    }
}
