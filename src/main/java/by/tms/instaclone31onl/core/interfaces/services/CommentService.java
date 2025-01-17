package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.dtos.CommentDto;

import java.util.UUID;

public interface CommentService {
    CommentDto addComment(UUID postId, UUID userId, String text);
}
