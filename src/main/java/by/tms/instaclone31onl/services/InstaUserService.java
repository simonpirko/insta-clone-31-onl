package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.models.entities.User;

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
        User user = this.userRepository.getBy(u->u.getLogin().equals(login) && u.getHash().equals(password));
        return user != null;
    }

    public boolean checkRegistr(String login, String password){
        List<UUID> user = this.userRepository.insert(List.of(User.builder().login(login).hash(password).build()));
        return !user.isEmpty();
    }

    public boolean checkLogin(String login) {
        User user = this.userRepository.getBy(u->u.getLogin().equals(login));
        return user != null;
    }

}
