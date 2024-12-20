package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.ReactionRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.models.dtos.CommentDto;
import by.tms.instaclone31onl.core.models.dtos.PostDto;
import by.tms.instaclone31onl.core.models.dtos.ReactionDto;
import by.tms.instaclone31onl.core.models.dtos.UserShortDto;
import by.tms.instaclone31onl.core.models.entities.BaseEntity;
import by.tms.instaclone31onl.core.models.entities.Comment;
import by.tms.instaclone31onl.core.models.entities.Post;
import by.tms.instaclone31onl.core.models.entities.Reaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InstaPostService implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ReactionRepository reactionRepository;

    public InstaPostService(PostRepository postRepository,
                            UserRepository userRepository,
                            CommentRepository commentRepository,
                            ReactionRepository reactionRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.reactionRepository = reactionRepository;
    }


    @Override
    public List<PostDto> getAll(Predicate<Post> predicate) {
        List<Post> posts = postRepository.getAllBy(predicate);

        List<PostDto> postDtos = new ArrayList<>();

        if (posts.isEmpty())
            return postDtos;

        Set<UUID> postIds = posts.stream().map(BaseEntity::getId).collect(Collectors.toSet());
        Set<UUID> usersIds = posts.stream().map(Post::getUserId).collect(Collectors.toSet());

        List<Reaction> reactions = reactionRepository.getAllBy(x -> postIds.contains(x.getPostId()));
        List<Comment> comments = commentRepository.getAllBy(x -> postIds.contains(x.getPostId()));
        Set<UUID> userReactionIds = reactions.stream().map(Reaction::getUserId).collect(Collectors.toSet());
        Set<UUID> userCommentIds = comments.stream().map(Comment::getUserId).collect(Collectors.toSet());

        //get user
        List<UserShortDto> users = userRepository.getAllBy(x -> usersIds.contains(x.getId())
                        || userCommentIds.contains(x.getId())
                        || userReactionIds.contains(x.getId()))
                .stream()
                .map(x -> new UserShortDto(x.getId(), x.getNickname(), x.getPhotos())).toList();

        for (Post post : posts) {
            List<ReactionDto> postLikes = reactions.stream()
                    .filter(x -> x.getPostId().equals(post.getId()))
                    .map(x -> {
                        UserShortDto reactionUser = users.stream().filter(u -> u.id().equals(x.getUserId())).findFirst().orElse(null);
                        return new ReactionDto(x.getId(), reactionUser, x.isLikeIt());
                    })
                    .toList();

            List<CommentDto> postComments = comments.stream()
                    .filter(x -> x.getPostId().equals(post.getId()))
                    .map(x -> {
                        UserShortDto commentUser = users.stream().filter(u -> u.id().equals(x.getUserId())).findFirst().orElse(null);
                        return new CommentDto(x.getId(), commentUser, x.getText(), x.getCreatedAt());
                    })
                    .toList();

            UserShortDto author = users.stream().filter(x -> x.id().equals(post.getUserId())).findFirst().orElse(null);
            PostDto postDto = new PostDto(post.getId(), author,
                    post.getLastUpdatedAt(), post.getDescription(),
                    post.getImages(), postLikes, postComments);
            postDtos.add(postDto);
        }
        return postDtos;
    }
}
