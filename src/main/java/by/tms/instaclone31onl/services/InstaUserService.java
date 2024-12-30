package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.models.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.UUID;

public class InstaUserService implements UserService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    public InstaUserService(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public boolean checkUser(String login, String password) {
        User user = this.userRepository.getBy(u->u.getLogin().equals(login) && BCrypt.checkpw(password, u.getHash()));
        return user != null;
    }

    public boolean saveUser(String login, String password){
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        List<UUID> user = this.userRepository.insert(List.of(User.builder().login(login).hash(hash).build()));
        return !user.isEmpty();
    }

    public boolean checkLogin(String login) {
        User user = this.userRepository.getBy(u->u.getLogin().equals(login));
        return user != null;
    }

    @Override
    public void editUser(User user) {

    }
}
