package by.tms.instaclone31onl.core.interfaces.factories;

import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.TodoRepository;

public interface RepositoryFactory {
    TodoRepository getTodoRepository();
    PostRepository getPostRepository();
}
