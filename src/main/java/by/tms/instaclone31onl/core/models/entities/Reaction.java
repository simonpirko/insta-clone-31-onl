package by.tms.instaclone31onl.core.models.entities;

import by.tms.instaclone31onl.core.annotations.Entity;
import com.opencsv.bean.CsvBindByPosition;
import lombok.experimental.SuperBuilder;

import java.util.UUID;
@Entity(name = "reactions", directories = "\\resources")
public class Reaction extends BaseEntity {
    @CsvBindByPosition(position = 1)
    private final UUID postId;
    @CsvBindByPosition(position = 2)
    private final UUID userId;
    @CsvBindByPosition(position = 3)
    private boolean likeIt;

    public Reaction(UUID id,
                   UUID postId,
                   boolean likeIt,
                   UUID userId) {
        super(id);
        this.postId = postId;
        this.likeIt = likeIt;
        this.userId = userId;
    }
    public UUID getPostId() {
        return postId;
    }
    public UUID getUserId() {
        return userId;
    }
    public boolean isLikeIt() {
        return likeIt;
    }
    public void setLikeIt(boolean likeIt) {
        this.likeIt = likeIt;
    }

    @Override
    public String[] getLine() {
        return new String[]{
                String.valueOf(id),
                String.valueOf(postId),
                String.valueOf(userId),
                String.valueOf(likeIt)
        };
    }
    public static Reaction fromLine(String[] line) {
        return new Reaction(UUID.fromString(line[0]),
                UUID.fromString(line[1]),
                Boolean.parseBoolean(line[3]),
                UUID.fromString(line[2]));
    }
}
