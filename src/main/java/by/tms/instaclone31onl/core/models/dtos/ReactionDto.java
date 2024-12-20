package by.tms.instaclone31onl.core.models.dtos;

import java.util.UUID;

public record ReactionDto(UUID id, UserShortDto user, boolean likeIt) {
}
