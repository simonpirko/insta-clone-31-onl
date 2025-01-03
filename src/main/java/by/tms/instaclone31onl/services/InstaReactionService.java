package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.ReactionRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.ReactionService;
import by.tms.instaclone31onl.core.models.dtos.ReactionDto;
import by.tms.instaclone31onl.core.models.dtos.UserShortDto;
import by.tms.instaclone31onl.core.models.entities.Post;
import by.tms.instaclone31onl.core.models.entities.Reaction;
import by.tms.instaclone31onl.core.models.entities.User;

import java.util.List;
import java.util.UUID;

public class InstaReactionService implements ReactionService {
    private final UserRepository userRepository;
    private final ReactionRepository reactionRepository;
    private final PostRepository postRepository;

    public InstaReactionService(ReactionRepository reactionRepository, PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.reactionRepository = reactionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ReactionDto addReaction(UUID postId, Boolean likeIt) {
        Post post = postRepository.getBy(p -> p.getId().equals(postId));
        List<UUID> reactionsId = reactionRepository.insert(List.of(new Reaction(null, postId, likeIt, post.getUserId()))); // Откуда взять UUID при добавлении?
        User user = userRepository.getBy(u -> u.getId().equals(post.getUserId()));

        return new ReactionDto(reactionsId.getFirst(), new UserShortDto(user.getId(), user.getNickname(), user.getPhotos()), likeIt);
    }

    @Override
    public ReactionDto editReaction(UUID reactionId, Boolean likeIt) {
        Reaction oldReaction = reactionRepository.getBy(reaction -> reaction.getId().equals(reactionId));
        Post post = postRepository.getBy(p -> p.getId().equals(oldReaction.getPostId()));
        Reaction updatedReaction = reactionRepository.update(new Reaction(null, reactionId, likeIt, post.getUserId()));
        User user = userRepository.getBy(u -> u.getId().equals(updatedReaction.getUserId()));

        return new ReactionDto(reactionId, new UserShortDto(user.getId(), user.getNickname(), user.getPhotos()), likeIt);
    }

    @Override
    public void deleteReaction(UUID reactionId) {
        reactionRepository.delete(reaction -> reaction.getId().equals(reactionId));
    }
}
