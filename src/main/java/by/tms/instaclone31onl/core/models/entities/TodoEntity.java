package by.tms.instaclone31onl.core.models.entities;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.interfaces.entities.CsvEntity;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.util.UUID;

@Entity(name = "todos")
public class TodoEntity implements CsvEntity {
    @CsvBindByPosition(position = 0)
    private UUID id;
    @CsvBindByPosition(position = 1)
    private final String title;
    @CsvBindByPosition(position = 2)
    private final String description;
    @CsvBindByPosition(position = 3)
    private final UUID ownerId;

    public TodoEntity(UUID id, String title, String description, UUID ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    @Override
    public String[] getCsvValue() {
        return new String[]{getId().toString(), getTitle(), getDescription(), getOwnerId().toString()};
    }
}
