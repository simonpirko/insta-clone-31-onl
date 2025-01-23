package by.tms.instaclone31onl.servlets.comments;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.CommentService;
import by.tms.instaclone31onl.core.models.requests.CommentCreateRequest;
import by.tms.instaclone31onl.core.utils.RequestUtils;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = ServletConstants.COMMENT_SERVLET_NAME, value = ServletConstants.COMMENT_API_SERVLET)
public class CommentServlet  extends BaseApiServlet {

    private final ServiceFactory serviceFactory;

    public CommentServlet()
    {
        serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected Object doPostApi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CommentCreateRequest comment = RequestUtils.getRequestBody(req, CommentCreateRequest.class);
        CommentService commentService = serviceFactory.getCommentService();
        return commentService.addComment(comment.postId(), currentUser, comment.text());
    }
}
