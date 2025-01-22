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
    private final ReactionRepository reactionRepository;

    public InstaReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    @Override
    public ReactionDto addReaction(User currentUser, UUID postId, Boolean likeIt) {
        List<UUID> reactionsId = reactionRepository.insert(List.of(new Reaction(null, postId, likeIt, currentUser.getId())));
        return new ReactionDto(reactionsId.getFirst(), new UserShortDto(currentUser.getId(), currentUser.getNickname(), currentUser.getPhotos()), likeIt);
    }


    @Override
    public ReactionDto editReaction(User currentUser, UUID reactionId, Boolean likeIt) {
        Reaction oldReaction = reactionRepository.getBy(reaction -> reaction.getId().equals(reactionId));
        oldReaction.setLikeIt(likeIt);
        reactionRepository.update(oldReaction);
        return new ReactionDto(reactionId, new UserShortDto(currentUser.getId(), currentUser.getNickname(), currentUser.getPhotos()), likeIt);
    }

    @Override
    public List<UUID> deleteReaction(UUID reactionId) {
        return reactionRepository.delete(reaction -> reaction.getId().equals(reactionId));
    }
}
