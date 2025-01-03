package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.entities.User;

public interface UserService {
    boolean checkUser(String login, String password);
    boolean insertUser(String login, String password);
    boolean checkLogin(String login);
    void editUser(User user);
    String checkUserRegistration(String login, String pass, String passCopy);
}
