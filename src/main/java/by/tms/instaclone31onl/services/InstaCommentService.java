package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.services.CommentService;

public class InstaCommentService implements CommentService {
    private final CommentRepository commentRepository;
    public InstaCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

}
