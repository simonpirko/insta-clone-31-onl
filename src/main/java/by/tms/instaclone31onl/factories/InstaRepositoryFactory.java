package by.tms.instaclone31onl.factories;

import by.tms.instaclone31onl.core.context.CsvContext;
import by.tms.instaclone31onl.core.interfaces.factories.RepositoryFactory;
import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.ReactionRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.models.entities.Comment;
import by.tms.instaclone31onl.core.models.entities.Post;
import by.tms.instaclone31onl.core.models.entities.Reaction;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.repositories.InstaCommentRepository;
import by.tms.instaclone31onl.repositories.InstaPostRepository;
import by.tms.instaclone31onl.repositories.InstaReactionRepository;
import by.tms.instaclone31onl.repositories.InstaUserRepository;

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


    public static InstaRepositoryFactory getInstance() {
        return instance == null
                ? new InstaRepositoryFactory(CsvContext.getInstance())
                : instance;
    }
}
