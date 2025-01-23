package by.tms.instaclone31onl.core.models.requests;

import java.util.UUID;

public record ReactionUpdateRequest(UUID reactionId, boolean likeIt) {
}
