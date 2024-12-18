package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.User;

public class InstaUserRepository
        extends BaseRepository<User>
        implements UserRepository
{
    public InstaUserRepository(CsvTable<User> csvTable) {
        super(csvTable);
    }

    @Override
    protected User mapper(String[] line) {
        return User.fromLine(line);
    }
}
