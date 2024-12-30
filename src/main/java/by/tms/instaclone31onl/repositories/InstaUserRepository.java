package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.User;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InstaUserRepository
        extends BaseRepository<User>
        implements UserRepository {
    public InstaUserRepository(CsvTable<User> csvTable) {
        super(csvTable);
    }

    @Override
    protected User mapper(String[] line) {
        return User.fromLine(line);
    }

    public User getUserByLogin(String login) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvTable.getPath()));
             CSVReader csvReader = new CSVReader(reader)) {
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                User user = mapper(nextRecord);
                if (user.getLogin().equals(login)) {
                    return user;
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
