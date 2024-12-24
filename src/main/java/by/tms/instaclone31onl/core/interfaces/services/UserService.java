package by.tms.instaclone31onl.core.interfaces.services;

public interface UserService {
    boolean checkUser(String login, String password);
    boolean checkRegistr(String login, String password);
    boolean checkLogin(String login);
}
