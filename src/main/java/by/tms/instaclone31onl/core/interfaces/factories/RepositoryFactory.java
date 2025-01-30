package by.tms.instaclone31onl.core.interfaces.factories;

import by.tms.instaclone31onl.core.interfaces.repositories.*;

public interface RepositoryFactory {
    PostRepository getPostRepository();
    UserRepository getUserRepository();
    CommentRepository getCommentRepository();
    ReactionRepository getReactionRepository();
    FriendRequestRepository getFriendRequestRepository();
}
