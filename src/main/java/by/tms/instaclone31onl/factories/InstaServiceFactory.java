package by.tms.instaclone31onl.factories;

import by.tms.instaclone31onl.core.interfaces.factories.RepositoryFactory;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.interfaces.services.TodoService;
import by.tms.instaclone31onl.services.InstaPostService;
import by.tms.instaclone31onl.services.InstaTodoService;

public class InstaServiceFactory implements ServiceFactory {

    private static ServiceFactory instance;
    private final RepositoryFactory repositoryFactory;
    private InstaServiceFactory(RepositoryFactory repositoryFactory){
        this.repositoryFactory = repositoryFactory;
        instance = this;
    }
    @Override
    public TodoService getTodoService() {
        return new InstaTodoService(repositoryFactory.getTodoRepository());
    }

    @Override
    public PostService getPostService() {
        return new InstaPostService(repositoryFactory.getPostRepository());
    }

    public static ServiceFactory getInstance() {
        return instance == null ? new InstaServiceFactory(InstaRepositoryFactory.getInstance()) : instance;
    }
}
