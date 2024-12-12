package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.TodoRepository;
import by.tms.instaclone31onl.core.interfaces.services.TodoService;
import by.tms.instaclone31onl.core.models.entities.TodoEntity;
import by.tms.instaclone31onl.core.models.request.TodoCreateRequest;
import by.tms.instaclone31onl.core.models.request.TodoUpdateRequest;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class InstaTodoService implements TodoService {

    public final TodoRepository todoRepository;

    public InstaTodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @Override
    public List<UUID> add(List<TodoCreateRequest> request) {
        List<TodoEntity> entities = request.stream()
                .map(x->new TodoEntity(null, x.title(), x.description(), x.ownerId())).toList();
        return todoRepository.insert(entities);
    }

    @Override
    public TodoEntity get(Predicate<TodoEntity> filter) {
        return todoRepository.get(filter);
    }

    @Override
    public List<TodoEntity> getAll(Predicate<TodoEntity> filter) {
        return todoRepository.getAll(filter);
    }

    @Override
    public List<UUID> delete(Predicate<TodoEntity> filter) {
        return todoRepository.delete(filter);
    }

    @Override
    public TodoEntity update(TodoUpdateRequest request) {
        TodoEntity entity = new TodoEntity(request.id(), request.title(), request.description(), request.ownerId());
        return todoRepository.update(entity);
    }
}
