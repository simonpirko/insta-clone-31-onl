package by.tms.instaclone31onl.factories;

import by.tms.instaclone31onl.core.context.CsvContext;
import by.tms.instaclone31onl.core.interfaces.factories.RepositoryFactory;
import by.tms.instaclone31onl.core.interfaces.repositories.*;
import by.tms.instaclone31onl.core.models.entities.*;
import by.tms.instaclone31onl.repositories.*;

public class InstaRepositoryFactory implements RepositoryFactory {

    private static InstaRepositoryFactory instance;

    private final CsvContext csvContext;
    private InstaRepositoryFactory(CsvContext csvContext) {
        this.csvContext = csvContext;
        instance = this;
    }

    @Override
    public PostRepository getPostRepository() {
        return new InstaPostRepository(csvContext.getCsvTable(Post.class));
    }

    @Override
    public UserRepository getUserRepository() {
        return new InstaUserRepository(csvContext.getCsvTable(User.class));
    }

    @Override
    public CommentRepository getCommentRepository() {
        return new InstaCommentRepository(csvContext.getCsvTable(Comment.class));
    }

    @Override
    public ReactionRepository getReactionRepository() {
        return new InstaReactionRepository(csvContext.getCsvTable(Reaction.class));
    }

    @Override
    public FriendRequestRepository getFriendRequestRepository() {
        return new InstaFriendRequestRepository(csvContext.getCsvTable(FriendRequest.class));
    }


    public static InstaRepositoryFactory getInstance() {
        return instance == null
                ? new InstaRepositoryFactory(CsvContext.getInstance())
                : instance;
    }
}
