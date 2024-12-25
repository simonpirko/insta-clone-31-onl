package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.entities.User;

public interface UserService {
    boolean checkUser(String login, String password);
    boolean checkRegistration(String login, String password);
    boolean checkLogin(String login);
    void editUser(User user);
}
