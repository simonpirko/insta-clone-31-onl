package by.tms.instaclone31onl.filters;

import by.tms.instaclone31onl.core.constants.AttributeConstants;
import by.tms.instaclone31onl.core.constants.ServletConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = "/*")
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String requestUrl = req.getRequestURI();
        if(req.getSession().getAttribute(AttributeConstants.CURRENT_USER) == null
                && ServletConstants.UNAUTHORIZED_URLS.stream().noneMatch(requestUrl ::startsWith)) {
            res.sendRedirect(ServletConstants.LOGIN_SERVLET);
        }
        else{
            chain.doFilter(req, res);
        }
    }
}

