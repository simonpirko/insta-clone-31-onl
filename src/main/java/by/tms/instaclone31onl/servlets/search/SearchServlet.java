package by.tms.instaclone31onl.servlets.search;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(ServletConstants.SEARCH_SERVLET)
public class SearchServlet extends BaseApiServlet {

    private final ServiceFactory serviceFactory;

    public SearchServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        if (text != null && !text.isBlank()) {
            List<User> allByNickname = serviceFactory.getUserService().getAllByNickname(text);
            request.setAttribute("users", allByNickname);
        }
        request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
    }
}

