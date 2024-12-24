package by.tms.instaclone31onl.factories;

import by.tms.instaclone31onl.core.interfaces.factories.RepositoryFactory;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.ReactionRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.CommentService;
import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.interfaces.services.ReactionService;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.services.InstaCommentService;
import by.tms.instaclone31onl.services.InstaPostService;
import by.tms.instaclone31onl.services.InstaReactionService;
import by.tms.instaclone31onl.services.InstaUserService;

public class InstaServiceFactory implements ServiceFactory{
    private final RepositoryFactory repositoryFactory;
    private static ServiceFactory instance;
    private InstaServiceFactory(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
        instance = this;
    }

    public static ServiceFactory getInstance(){
        return instance == null? new InstaServiceFactory(InstaRepositoryFactory.getInstance()) : instance;
    }

    @Override
    public ReactionService getReactionService() {
        ReactionRepository reactionRepository = repositoryFactory.getReactionRepository();
        return new InstaReactionService(reactionRepository);
    }

    @Override
    public PostService getPostService() {
        PostRepository postRepository = repositoryFactory.getPostRepository();
        CommentRepository commentRepository = repositoryFactory.getCommentRepository();
        UserRepository userRepository = repositoryFactory.getUserRepository();
        ReactionRepository reactionRepository = repositoryFactory.getReactionRepository();
        return new InstaPostService(postRepository, userRepository, commentRepository, reactionRepository);
    }

    @Override
    public CommentService getCommentService() {
        CommentRepository commentRepository = repositoryFactory.getCommentRepository();
        return new InstaCommentService(commentRepository);
    }

    @Override
    public UserService getUserService() {
        UserRepository userRepository = repositoryFactory.getUserRepository();
        CommentRepository commentRepository = repositoryFactory.getCommentRepository();
        return new InstaUserService(userRepository, commentRepository);
    }
}
