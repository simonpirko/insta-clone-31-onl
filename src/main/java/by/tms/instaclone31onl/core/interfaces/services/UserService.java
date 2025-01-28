package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    boolean insertUser(String login, String password);
    boolean checkLogin(String login);
    User getUser(String logi, String passwordn);
    void editUser(User user);
    User getUserById(UUID uuid);
    List<User> getAllByNickname(String nickname);
}
