package by.tms.instaclone31onl.factories;

import by.tms.instaclone31onl.core.contexts.CsvContext;
import by.tms.instaclone31onl.core.interfaces.factories.RepositoryFactory;
import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.TodoRepository;
import by.tms.instaclone31onl.core.models.entities.PostEntity;
import by.tms.instaclone31onl.core.models.entities.TodoEntity;
import by.tms.instaclone31onl.repositories.InstaPostRepository;
import by.tms.instaclone31onl.repositories.InstaTodoRepository;

public class InstaRepositoryFactory implements RepositoryFactory {

    private static RepositoryFactory instance;
    private final CsvContext csvContext;
    private InstaRepositoryFactory(CsvContext context) {
        this.csvContext = context;
        instance = this;
    }

    @Override
    public TodoRepository getTodoRepository() {
        return new InstaTodoRepository(csvContext.getCsvTable(TodoEntity.class));
    }

    @Override
    public PostRepository getPostRepository() {
        return new InstaPostRepository(csvContext.getCsvTable(PostEntity.class));
    }

    public static RepositoryFactory getInstance() {
        return instance == null ? new InstaRepositoryFactory(CsvContext.getInstance()) : instance;
    }
}
