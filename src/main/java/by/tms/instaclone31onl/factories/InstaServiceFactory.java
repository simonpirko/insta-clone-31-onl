package by.tms.instaclone31onl.factories;

import by.tms.instaclone31onl.core.interfaces.factories.RepositoryFactory;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
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
        return new InstaReactionService(repositoryFactory.getReactionRepository(),
                repositoryFactory.getPostRepository(), repositoryFactory.getUserRepository());
    }

    @Override
    public PostService getPostService() {
        return new InstaPostService(
                repositoryFactory.getPostRepository(),
                repositoryFactory.getUserRepository(),
                repositoryFactory.getCommentRepository(),
                repositoryFactory.getReactionRepository());
    }

    @Override
    public CommentService getCommentService() {
        return new InstaCommentService(repositoryFactory.getCommentRepository());
    }

    @Override
    public UserService getUserService() {
        return new InstaUserService(
                repositoryFactory.getUserRepository(),
                repositoryFactory.getCommentRepository());
    }
}
