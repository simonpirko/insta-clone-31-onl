package by.tms.instaclone31onl.core.models.requests;

import by.tms.instaclone31onl.core.enums.PostStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostCreateRequest {
    private UUID userId;
    private List<String> photos;
    private PostStatus status;
    private String description;
}
