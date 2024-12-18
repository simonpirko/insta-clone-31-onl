package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.ReactionRepository;
import by.tms.instaclone31onl.core.interfaces.services.ReactionService;

public class InstaReactionService implements ReactionService {
    private final ReactionRepository reactionRepository;
    public InstaReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }
}
