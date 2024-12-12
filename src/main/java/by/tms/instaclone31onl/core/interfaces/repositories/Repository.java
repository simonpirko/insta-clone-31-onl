package by.tms.instaclone31onl.core.interfaces.repositories;

import by.tms.instaclone31onl.core.interfaces.entities.CsvEntity;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface Repository<T extends CsvEntity> {
    List<T> getAll(Predicate<T> filter);
    T get(Predicate<T> filter);
    List<UUID> insert(List<T> items);
    T update(T model);
    List<UUID> delete(Predicate<T> filter);
}
