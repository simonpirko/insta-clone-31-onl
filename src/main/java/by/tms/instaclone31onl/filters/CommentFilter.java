package by.tms.instaclone31onl.filters;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = ServletConstants.COMMENT_API_SERVLET)
public class CommentFilter extends HttpFilter {
    private final int maxLength = 1000;

//    public CommentFilter(int maxLength) {
//        this.maxLength = maxLength;
//    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String text = req.getParameter("text");
        if (text != null && text.length() > maxLength) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Comment length exceeds the maximum allowed length of "
                    + maxLength + " characters.");
        }else{
            chain.doFilter(req, res);
        }
    }
}

