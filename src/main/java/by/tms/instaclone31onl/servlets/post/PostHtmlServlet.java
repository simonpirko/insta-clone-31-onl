package by.tms.instaclone31onl.servlets.post;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = ServletConstants.POST_SERVLET_NAME, value = ServletConstants.POST_SERVLET)
public class PostHtmlServlet extends BaseApiServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/posts.jsp").forward(request, response);
    }
}
