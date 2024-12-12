package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.entities.TodoEntity;
import by.tms.instaclone31onl.core.models.request.TodoCreateRequest;
import by.tms.instaclone31onl.core.models.request.TodoUpdateRequest;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface TodoService {
    List<UUID> add(List<TodoCreateRequest> request);
    TodoEntity get(Predicate<TodoEntity> filter);
    List<TodoEntity> getAll(Predicate<TodoEntity> filter);
    List<UUID> delete(Predicate<TodoEntity> filter);
    TodoEntity update(TodoUpdateRequest request);
}
