package by.tms.instaclone31onl.factories;

import by.tms.instaclone31onl.core.interfaces.factories.RepositoryFactory;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.*;
import by.tms.instaclone31onl.services.*;

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
        return new InstaReactionService(repositoryFactory.getReactionRepository());
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
        return new InstaUserService(repositoryFactory.getUserRepository());
    }

    @Override
    public FileService getFileService(){
        return new InstaFileService();
    }

    @Override
    public FriendRequestService getFriendRequestService() {
        return new InstaFriendRequestService(repositoryFactory.getFriendRequestRepository(),
                repositoryFactory.getUserRepository());
    }

}
