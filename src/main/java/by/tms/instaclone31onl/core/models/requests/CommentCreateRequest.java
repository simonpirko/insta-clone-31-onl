package by.tms.instaclone31onl.core.models.requests;

import java.util.UUID;

public record CommentCreateRequest(String text, UUID postId) {
}
