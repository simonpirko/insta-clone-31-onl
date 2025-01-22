package by.tms.instaclone31onl.servlets.reaction;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.ReactionService;
import by.tms.instaclone31onl.core.models.requests.ReactionCreateRequest;
import by.tms.instaclone31onl.core.models.requests.ReactionUpdateRequest;
import by.tms.instaclone31onl.core.utils.RequestUtils;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;
@WebServlet(name = ServletConstants.REACTION_SERVLET_NAME, value = ServletConstants.REACTION_API_SERVLET)
public class ReactionServlet extends BaseApiServlet {
    private final ServiceFactory serviceFactory;

    public ReactionServlet() {
        this.serviceFactory = InstaServiceFactory.getInstance();
    }
    @Override
    protected Object doPostApi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ReactionService reactionService = serviceFactory.getReactionService();
        ReactionCreateRequest createRequest = RequestUtils.getRequestBody(req, ReactionCreateRequest.class);
        return reactionService.addReaction(currentUser, createRequest.postId(), createRequest.likeIt());
    }

    @Override
    protected Object doPutApi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ReactionService reactionService = serviceFactory.getReactionService();
        ReactionUpdateRequest updateRequest = RequestUtils.getRequestBody(req, ReactionUpdateRequest.class);
        return reactionService.editReaction(currentUser, updateRequest.reactionId(), updateRequest.likeIt());
    }

    @Override
    protected Object doDeleteApi(HttpServletRequest req, HttpServletResponse resp) {
        ReactionService reactionService = serviceFactory.getReactionService();
        UUID reactionId = UUID.fromString(req.getParameter("reactionId"));
        return reactionService.deleteReaction(reactionId);
    }
}


