package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.CommentService;
import by.tms.instaclone31onl.core.models.dtos.CommentDto;
import by.tms.instaclone31onl.core.models.dtos.UserShortDto;
import by.tms.instaclone31onl.core.models.entities.Comment;
import by.tms.instaclone31onl.core.models.entities.Post;
import by.tms.instaclone31onl.core.models.entities.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class InstaCommentService implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public InstaCommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto addComment(UUID postId, UUID userId, String text) {
        Post post = postRepository.getBy(p -> p.getId().equals(postId));
        Comment newComment = new Comment(null, postId, text, post.getUserId(), LocalDateTime.now());
        List<UUID> commentIds = commentRepository.insert(List.of(newComment));
        UUID commentId = commentIds.get(0);
        User user = userRepository.getBy(u -> u.getId().equals(userId));
        return new CommentDto(commentId, new UserShortDto(user.getId(), user.getNickname(), user.getPhotos()), text,  newComment.getCreatedAt());
    }
}
