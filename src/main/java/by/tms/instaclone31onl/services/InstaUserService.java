package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.models.entities.User;

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
}
