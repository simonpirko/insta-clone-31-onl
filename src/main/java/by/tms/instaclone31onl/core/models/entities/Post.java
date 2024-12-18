package by.tms.instaclone31onl.core.models.entities;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.constants.DateTimeConstants;
import by.tms.instaclone31onl.core.enums.PostStatus;
import by.tms.instaclone31onl.core.interfaces.entities.DateUpdatable;
import by.tms.instaclone31onl.core.utils.JsonConverter;
import com.opencsv.bean.CsvBindByPosition;
import org.reflections.serializers.JsonSerializer;

import javax.swing.text.DateFormatter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "posts", directories = "\\resources")
public class Post extends BaseEntity implements DateUpdatable{
    @CsvBindByPosition(position = 1)
    private final UUID userId;
    @CsvBindByPosition(position = 2)
    private final String description;
    @CsvBindByPosition(position = 3)
    private List<String> images;
    @CsvBindByPosition(position = 4)
    private LocalDate lastUpdatedAt;
    @CsvBindByPosition(position = 5)
    private final PostStatus status;

    public Post(UUID id,
                UUID userId,
                String description,
                List<String> images,
                LocalDate lastUpdatedAt,
                PostStatus status) {
        super(id);
        this.userId = userId;
        this.description = description;
        this.images = images;
        this.lastUpdatedAt = lastUpdatedAt;
        this.status = status;
    }
    public UUID getUserId() {
        return userId;
    }
    public String getDescription() {
        return description;
    }
    public List<String> getImages() {
        return images;
    }
    public LocalDate getLastUpdatedAt() {
        return lastUpdatedAt;
    }
    public PostStatus getStatus() {
        return status;
    }

    public static Post fromLine(String[] line){
        List<String> images = JsonConverter.deserialize(line[3],List.class);
        return  new Post(UUID.fromString(line[0]),
                UUID.fromString(line[1]),
                line[2],
                images,
                LocalDate.parse(line[4],DateTimeFormatter.ofPattern(DateTimeConstants.DATE_TIME_FORMAT)),
                PostStatus.getStatusByValue(line[5]));
    }
    @Override
    public String[] getLine() {
        return new String[]{
                String.valueOf(id),
                String.valueOf(userId),
                description,
                JsonConverter.serialize(images),
                lastUpdatedAt.format(DateTimeFormatter.ofPattern(DateTimeConstants.DATE_TIME_FORMAT)),
                status.getStatusValue()
        };
    }

    @Override
    public void setDate(LocalDate date) {
        lastUpdatedAt = date;
    }
}
