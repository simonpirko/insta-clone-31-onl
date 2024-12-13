package by.tms.instaclone31onl.core.models.entities;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.constants.DateConstants;
import by.tms.instaclone31onl.core.enums.PostStatus;
import by.tms.instaclone31onl.core.interfaces.entities.CsvEntity;
import by.tms.instaclone31onl.core.utils.JsonConverter;
import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity(name = "posts")
public class PostEntity implements CsvEntity {
    @CsvBindByPosition(position = 0)
    private UUID id;
    @CsvBindByPosition(position = 1)
    private final UUID userId;
    @CsvBindByPosition(position = 2)
    private final String title;
    @CsvBindByPosition(position = 3)
    private final String body;
    @CsvBindByPosition(position = 4)
    private final PostStatus status;
    @CsvBindByPosition(position = 5)
    private List<UUID> commentIds;
    @CsvBindByPosition(position = 6)
    private List<UUID> reactionIds;
    @CsvBindByPosition(position = 7)
    private final LocalDate createdAt;
    @CsvBindByPosition(position = 8)
    private LocalDate updatedAt;
    @CsvBindByPosition(position = 9)
    private List<String> images;

    public PostEntity(UUID id,
                      UUID userId,
                      String title,
                      String body,
                      PostStatus status,
                      List<UUID> commentIds,
                      List<UUID> reactionIds,
                      LocalDate createdAt,
                      LocalDate updatedAt,
                      List<String> images) {

        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reactionIds = reactionIds != null ? reactionIds : new ArrayList<>();
        this.commentIds = commentIds != null ? commentIds : new ArrayList<>();
        this.images = images != null ? images : new ArrayList<>();
    }

    public void setCommentIds(UUID... commentIds) {
        this.commentIds.addAll(Arrays.stream(commentIds).toList());
    }

    public void setImages(String... images) {
        this.images.addAll(Arrays.stream(images).toList());
    }

    public void setReactionIds(UUID... reactionIds) {
        this.reactionIds.addAll(Arrays.stream(reactionIds).toList());
    }
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String[] getCsvValue() {
        return List.of(String.valueOf(id),
                String.valueOf(userId),
                title,
                body,
                status.toString(),
                JsonConverter.serialize(commentIds),
                JsonConverter.serialize(reactionIds),
                createdAt.format(DateTimeFormatter.ofPattern(DateConstants.DATE_FORMAT)),
                updatedAt.format(DateTimeFormatter.ofPattern(DateConstants.DATE_FORMAT)),
                JsonConverter.serialize(this.images)
                ).toArray(new String[0]);
    }

    public List<String> getImages() {
        return images;
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
    public PostStatus getStatus() {
        return status;
    }
    public List<UUID> getCommentIds() {
        return commentIds;
    }
    public List<UUID> getReactionIds() {
        return reactionIds;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}
