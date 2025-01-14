package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.models.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class InstaUserService implements UserService {
    private final UserRepository userRepository;
    public InstaUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String login, String password){
        return userRepository.getBy(user->user.getLogin().equals(login) && BCrypt.checkpw(password, user.getHash()));
    }

    public boolean insertUser(String login, String password){
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        List<UUID> user = this.userRepository.insert(List.of(User.builder().login(login).hash(hash).nickname(login).build()));
        return !user.isEmpty();
    }

    @Override
    public boolean checkLogin(String login) {
        User user = this.userRepository.getBy(u->u.getLogin().equals(login));
        return Objects.isNull(user);
    }

    @Override
    public void editUser(User user) {

    }
}
