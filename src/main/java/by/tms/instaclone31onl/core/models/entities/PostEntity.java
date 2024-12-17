package by.tms.instaclone31onl.core.models.entities;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.interfaces.entities.CsvEntity;
import com.opencsv.bean.CsvBindByPosition;

import java.util.List;
import java.util.UUID;

/**
 * @author Buyevich_I
 */
@Entity(name = "posts", directories = "\\resources")
public class PostEntity implements CsvEntity {
    @CsvBindByPosition(position = 0)
    private UUID id;
    @CsvBindByPosition(position = 1)
    private final UUID userId;
    @CsvBindByPosition(position = 2)
    private final String title;
    @CsvBindByPosition(position = 3)
    private final String body;

    public PostEntity(UUID id,
                      UUID userId,
                      String title,
                      String body) {

        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    @Override
    public String[] getCsvValue() {
        return List.of(String.valueOf(id),
                String.valueOf(userId),
                title,
                body
        ).toArray(new String[0]);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
}
