package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.dtos.CommentDto;
import by.tms.instaclone31onl.core.models.entities.User;

import java.util.UUID;

public interface CommentService {
    CommentDto addComment(UUID postId, User currentUser, String text);
}
