package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.UserService;

public class InstaUserService implements UserService {
    private final UserRepository userRepository;
    public InstaUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
