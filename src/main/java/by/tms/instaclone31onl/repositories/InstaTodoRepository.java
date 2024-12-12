package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.repositories.TodoRepository;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.TodoEntity;

import java.util.UUID;

public class InstaTodoRepository
        extends InstaRepository<TodoEntity>
        implements TodoRepository
{

    public InstaTodoRepository(CsvTable<TodoEntity> table) {
        super(table);
    }

    @Override
    protected TodoEntity mapper(String[] values) {
        if (values == null || values.length < 1)
            return null;

        return new TodoEntity(UUID.fromString(values[0]), values[1], values[2], UUID.fromString(values[3]));
    }

}
