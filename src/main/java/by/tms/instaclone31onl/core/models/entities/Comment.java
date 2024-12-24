package by.tms.instaclone31onl.core.models.entities;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.constants.DateTimeConstants;
import by.tms.instaclone31onl.core.interfaces.entities.DateUpdatable;
import com.opencsv.bean.CsvBindByPosition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "comments", directories = "\\resources")
public class Comment extends BaseEntity implements DateUpdatable {
    @CsvBindByPosition(position = 1)
    private final UUID postId;
    @CsvBindByPosition(position = 2)
    private final UUID userId;
    @CsvBindByPosition(position = 3)
    private final String text;
    @CsvBindByPosition(position = 4)
    private LocalDateTime createdAt;

    public Comment(UUID id,
                   UUID postId,
                   String text,
                   UUID userId,
                   LocalDateTime createdAt) {
        super(id);
        this.postId = postId;
        this.text = text;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public UUID getPostId() {
        return postId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public static Comment fromLine(String[] line) {
        return new Comment(UUID.fromString(line[0]),
                UUID.fromString(line[1]),
                line[2],
                UUID.fromString(line[3]),
                LocalDateTime.parse(line[4], DateTimeFormatter.ofPattern(DateTimeConstants.DATE_TIME_FULL_FORMAT)));
    }

    @Override
    public String[] getLine() {
        return new String[]{
                String.valueOf(id),
                String.valueOf(postId),
                text,
                String.valueOf(userId),
                createdAt.format(DateTimeFormatter.ofPattern(DateTimeConstants.DATE_TIME_FULL_FORMAT))
        };
    }

    @Override
    public void setDate(LocalDateTime date) {
        createdAt = date;
    }
}
