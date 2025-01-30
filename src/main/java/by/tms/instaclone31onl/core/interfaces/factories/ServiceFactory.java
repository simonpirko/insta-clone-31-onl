package by.tms.instaclone31onl.core.interfaces.factories;

import by.tms.instaclone31onl.core.interfaces.services.*;

public interface ServiceFactory {
    ReactionService getReactionService();
    PostService getPostService();
    CommentService getCommentService();
    UserService getUserService();
    FileService getFileService();
    FriendRequestService getFriendRequestService();
}
