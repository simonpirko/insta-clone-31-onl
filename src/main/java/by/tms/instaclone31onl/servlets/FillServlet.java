package by.tms.instaclone31onl.servlets;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.enums.PostStatus;
import by.tms.instaclone31onl.core.interfaces.factories.RepositoryFactory;
import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.ReactionRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.models.entities.Comment;
import by.tms.instaclone31onl.core.models.entities.Post;
import by.tms.instaclone31onl.core.models.entities.Reaction;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.factories.InstaRepositoryFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@WebServlet(ServletConstants.FILL_DATA_SERVLET)
public class FillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RepositoryFactory factory = InstaRepositoryFactory.getInstance();
        UserRepository userRepository = factory.getUserRepository();
        PostRepository postRepository = factory.getPostRepository();
        ReactionRepository reactionRepository = factory.getReactionRepository();
        CommentRepository commentRepository = factory.getCommentRepository();
        //delete all
        userRepository.delete(x->true);
        postRepository.delete(x->true);
        reactionRepository.delete(x->true);
        commentRepository.delete(x->true);

        List<User> userEntities = IntStream.range(1, 100).boxed()
                .map(x ->{

                    String salt = BCrypt.gensalt();
                    String hash = BCrypt.hashpw("login" + x, salt);
                    return new User(null, "login" + x, hash,
                        "nickname" + x, null, null,
                        List.of("https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (24).webp",
                                "https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (24).webp",
                                "https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (24).webp",
                                "https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (24).webp",
                                "https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (24).webp"),
                             "Some greate description for user login"+x);
                })
                .toList();

        //save data

        List<UUID> userIds = userRepository.insert(userEntities);

        //generation post

        Random random = new Random();
        List<Post> postEntites = IntStream.range(1, 100).boxed()
                .map(x->{
                    List<String> images = List.of("https://mdbcdn.b-cdn.net/img/new/standard/nature/184.webp",
                            "https://mdbcdn.b-cdn.net/img/new/standard/nature/184.webp",
                            "https://mdbcdn.b-cdn.net/img/new/standard/nature/183.webp",
                            "https://mdbcdn.b-cdn.net/img/new/standard/nature/182.webp",
                            "https://mdbcdn.b-cdn.net/img/new/standard/nature/181.webp");
                    UUID userId = userIds.get(random.nextInt(userIds.size()));
                    return new Post(null, userId,
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                            images,null, PostStatus.PUBLISHED);
                }).toList();


        List<UUID> postIds = postRepository.insert(postEntites);

        //generate reactions
        List<Reaction> reactionEntities = IntStream.range(1, 5000).boxed()
                .map(x->{
                    UUID userId = userIds.get(random.nextInt(userIds.size()));
                    UUID postId = postIds.get(random.nextInt(postIds.size()));
                    return new Reaction(null, postId,x % 2 == 0, userId);
                }).toList();


        List<UUID> reactionIds = reactionRepository.insert(reactionEntities);

        //generate commets
        List<Comment> commentEntities = IntStream.range(1, 5000).boxed()
                .map(x->{
                    UUID userId = userIds.get(random.nextInt(userIds.size()));
                    UUID postId = postIds.get(random.nextInt(postIds.size()));
                    return new Comment(null, postId,"Commentary text"+ x, userId, null);
                }).toList();


        commentRepository.insert(commentEntities);
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }
}
