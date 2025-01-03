package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.dtos.ReactionDto;

import java.util.UUID;

public interface ReactionService {
    ReactionDto addReaction(UUID postId, Boolean likeIt);
    ReactionDto editReaction(UUID reactionId, Boolean likeIt);
    void deleteReaction(UUID reactionId);
}