package by.tms.instaclone31onl.filters;

import by.tms.instaclone31onl.core.interfaces.services.UserService;
import by.tms.instaclone31onl.core.utils.StringUtils;
import by.tms.instaclone31onl.factories.InstaServiceFactory;

public class RegistrationFilter {

    /**
     * Проверка на заполненность полей при регистрации юзера, если такого юзера нет, добавляем нового
     *
     * @param login    логин
     * @param pass     пароль
     * @param passCopy копия пароля
     * @return null - если нет ошибки
     */
    public static String checkUserRegistration(String login, String pass, String passCopy) {
        UserService userService = InstaServiceFactory.getInstance().getUserService();
        if (StringUtils.isEmpty(login) ||
                StringUtils.isEmpty(pass) ||
                StringUtils.isEmpty(passCopy)) {
            return "Заполните все поля";
        } else {
            if (pass.equals(passCopy)) {
                if (userService.checkLogin(login)) {
                    userService.insertUser(login, pass);
                    return null;
                }
                return "Такой пользователь уже существует";
            }
            return "Пароли не совпадают";
        }
    }
}
