package by.tms.instaclone31onl.core.interfaces.repositories;

import by.tms.instaclone31onl.core.models.entities.BaseEntity;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T extends BaseEntity, I> {
    List<T> getAllBy(Predicate<T> predicate);
    T getBy(Predicate<T> predicate);
    List<I> insert(List<T> models);
    List<I> delete(Predicate<T> predicate);
    T update(T entity);
}
