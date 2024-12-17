package by.tms.instaclone31onl.core.interfaces.entities;

import java.util.UUID;

public interface CsvEntity {
    String[] getCsvValue();
    UUID getId();
    void setId(UUID id);
}
