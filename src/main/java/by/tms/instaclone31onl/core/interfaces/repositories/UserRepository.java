package by.tms.instaclone31onl.core.interfaces.repositories;

import by.tms.instaclone31onl.core.models.entities.User;

import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {
    User getUserByLogin(String login);
}
