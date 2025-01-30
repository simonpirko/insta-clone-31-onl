package by.tms.instaclone31onl.core.models.entities;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.constants.DateTimeConstants;
import by.tms.instaclone31onl.core.interfaces.entities.DateUpdatable;
import com.opencsv.bean.CsvBindByPosition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "requests", directories = "\\resources")
public class FriendRequest extends BaseEntity implements DateUpdatable {
    @CsvBindByPosition(position = 1)
    private UUID requesterId;
    @CsvBindByPosition(position = 2)
    private UUID receiverId;
    @CsvBindByPosition(position = 3)
    private LocalDateTime createdAt;
    public FriendRequest(UUID id, UUID requesterId, UUID receiverId, LocalDateTime createdAt) {
        super(id);
        this.requesterId = requesterId;
        this.receiverId = receiverId;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getRequesterId() {
        return requesterId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public static FriendRequest fromLine(String[] line) {
        return new FriendRequest(UUID.fromString(line[0]),
                UUID.fromString(line[1]),
                UUID.fromString(line[2]),
                LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern(DateTimeConstants.DATE_TIME_FULL_FORMAT)));
    }

    @Override
    public String[] getLine() {
        return new String[]{
                String.valueOf(id),
                String.valueOf(requesterId),
                String.valueOf(receiverId),
                createdAt.format(DateTimeFormatter.ofPattern(DateTimeConstants.DATE_TIME_FULL_FORMAT))
        };
    }

    @Override
    public void setDate(LocalDateTime date) {
        createdAt=date;
    }
}
