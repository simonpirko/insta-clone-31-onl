package by.tms.instaclone31onl.core.interfaces.factories;

import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.ReactionRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;

public interface RepositoryFactory {
    PostRepository getPostRepository();
    UserRepository getUserRepository();
    CommentRepository getCommentRepository();
    ReactionRepository getReactionRepository();
}
