package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.core.utils.StringUtils;
import by.tms.instaclone31onl.factories.InstaServiceFactory;

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

    public boolean insertUser(String login, String password){
        List<UUID> user = this.userRepository.insert(List.of(User.builder().login(login).hash(password).build()));
        return !user.isEmpty();
    }

    public boolean checkLogin(String login) {
        User user = this.userRepository.getBy(u->u.getLogin().equals(login));
        return user != null;
    }

    @Override
    public void editUser(User user) {

    }

    /**
     * Проверка на заполненность полей при регистрации юзера
     * @param login логин
     * @param pass пароль
     * @param passCopy копия пароля
     * @return ошибку или null, если её нет
     */
    public String checkUserRegistration(String login, String pass, String passCopy) {
        ServiceFactory serviceFactory = InstaServiceFactory.getInstance();
        if (StringUtils.isEmpty(pass) ||
                StringUtils.isEmpty(passCopy) ||
                StringUtils.isEmpty(login)) {
            return "Заполните все поля";
        } else {
            if (serviceFactory.getUserService().checkLogin(login)) {
                return "Такое имя пользователя уже существует";
            } else {
                if (serviceFactory.getUserService().insertUser(login, pass)) {
                    return null;
                } else {
                    return "Пароли не совпадают";
                }
            }
        }
    }
}
