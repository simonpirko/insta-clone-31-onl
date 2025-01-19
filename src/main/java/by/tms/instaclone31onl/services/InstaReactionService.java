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
    public ReactionDto addReaction(User currentUser, UUID postId, Boolean likeIt) {
        Post post = postRepository.getBy(p -> p.getId().equals(postId));
        List<UUID> reactionsId = reactionRepository.insert(List.of(new Reaction(null, postId, likeIt, post.getUserId())));

        return new ReactionDto(reactionsId.getFirst(), new UserShortDto(currentUser.getId(), currentUser.getNickname(), currentUser.getPhotos()), likeIt);
    }


    @Override
    public ReactionDto editReaction(User currentUser, UUID reactionId, Boolean likeIt) {
        Reaction oldReaction = reactionRepository.getBy(reaction -> reaction.getId().equals(reactionId));
        Post post = postRepository.getBy(p -> p.getId().equals(oldReaction.getPostId()));
        reactionRepository.update(new Reaction(null, reactionId, likeIt, post.getUserId()));

        return new ReactionDto(reactionId, new UserShortDto(currentUser.getId(), currentUser.getNickname(), currentUser.getPhotos()), likeIt);
    }

    @Override
    public List<UUID> deleteReaction(UUID reactionId) {
        return reactionRepository.delete(reaction -> reaction.getId().equals(reactionId));
    }
}
