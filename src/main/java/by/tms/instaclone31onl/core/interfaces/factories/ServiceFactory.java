package by.tms.instaclone31onl.core.interfaces.factories;

import by.tms.instaclone31onl.core.interfaces.services.CommentService;
import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.interfaces.services.ReactionService;
import by.tms.instaclone31onl.core.interfaces.services.UserService;

public interface ServiceFactory {
    ReactionService getReactionService();
    PostService getPostService();
    CommentService getCommentService();
    UserService getUserService();
}
