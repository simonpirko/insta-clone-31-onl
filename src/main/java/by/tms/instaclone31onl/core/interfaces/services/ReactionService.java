package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.dtos.ReactionDto;
import by.tms.instaclone31onl.core.models.entities.User;

import java.util.List;
import java.util.UUID;

public interface ReactionService {
    ReactionDto addReaction(User currentUser, UUID postId, Boolean likeIt);
    ReactionDto editReaction(User currentUser, UUID reactionId, Boolean likeIt);
    List<UUID> deleteReaction(UUID reactionId);
}