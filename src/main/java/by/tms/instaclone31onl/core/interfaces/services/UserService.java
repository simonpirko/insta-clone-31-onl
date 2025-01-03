package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.entities.User;

public interface UserService {
    boolean insertUser(String login, String password);
    User getUser(String login, String password);
    void editUser(User user);
}
