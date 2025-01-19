package by.tms.instaclone31onl.servlets.reaction;

import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.ReactionService;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class ReactionServlet extends BaseApiServlet {
    private final ServiceFactory serviceFactory;
    private final ReactionService reactionService;

    public ReactionServlet(final ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        reactionService = serviceFactory.getReactionService();
    }
    @Override
    protected Object doPostApi(HttpServletRequest req, HttpServletResponse resp) {
        UUID postId = UUID.fromString(req.getParameter("postId"));
        Boolean likeIt = Boolean.valueOf(req.getParameter("likeIt"));

        return reactionService.addReaction(currentUser, postId, likeIt);
    }

    @Override
    protected Object doPutApi(HttpServletRequest req, HttpServletResponse resp) {
        UUID reactionId = UUID.fromString(req.getParameter("reactionId"));
        Boolean likeIt = Boolean.valueOf(req.getParameter("likeIt"));

        return reactionService.editReaction(currentUser, reactionId, likeIt);
    }

    @Override
    protected Object doDeleteApi(HttpServletRequest req, HttpServletResponse resp) {
        UUID reactionId = UUID.fromString(req.getParameter("reactionId"));

        return reactionService.deleteReaction(reactionId);
    }
}


