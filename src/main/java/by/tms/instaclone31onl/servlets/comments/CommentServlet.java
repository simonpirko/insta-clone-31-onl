package by.tms.instaclone31onl.servlets.comments;

import by.tms.instaclone31onl.core.interfaces.services.CommentService;
import by.tms.instaclone31onl.core.models.dtos.UserShortDto;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class CommentServlet  extends BaseApiServlet {

    private final CommentService commentService;

    public CommentServlet(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    protected Object doPostApi(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UUID postId = UUID.fromString(req.getParameter("postId"));
        String text = req.getParameter("text");
        UUID userId = UUID.fromString(req.getParameter("userId"));
        return commentService.addComment(postId, userId, text);
    }
}
