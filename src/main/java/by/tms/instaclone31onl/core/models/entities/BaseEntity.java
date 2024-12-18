package by.tms.instaclone31onl.core.models.entities;

import com.opencsv.bean.CsvBindByPosition;

import java.util.UUID;

public abstract class BaseEntity {

    @CsvBindByPosition(position = 0)
    protected UUID id;

    public BaseEntity(UUID id) {
        this.id = id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }

    public abstract String[] getLine();
}
