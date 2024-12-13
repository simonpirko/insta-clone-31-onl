package by.tms.instaclone31onl.core.interfaces.factories;

import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.interfaces.services.TodoService;

public interface ServiceFactory {
    TodoService getTodoService();
    PostService getPostService();
}
