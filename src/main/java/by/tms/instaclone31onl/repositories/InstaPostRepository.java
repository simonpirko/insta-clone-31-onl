package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.Post;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class InstaPostRepository
        extends BaseRepository<Post>
    implements PostRepository
{
    public InstaPostRepository(CsvTable<Post> csvTable) {
        super(csvTable);
    }

    @Override
    protected Post mapper(String[] line) {
        return Post.fromLine(line);
    }

    @Override
    public List<Post> getPagedList(Predicate<Post> predicate, int start, int count) {

        int skip = start * count;

        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvTable.getPath()));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            List<Post> entities = new ArrayList<>();
            String[] nextRecord;
            for (int line = 0; (nextRecord = csvReader.readNext()) != null;) {
                Post obj = mapper(nextRecord);
                if(entities.size() >= count){
                    break;
                }
                if (predicate == null || predicate.test(obj)) {
                    if(line < skip)
                    {
                        line++;
                        continue;
                    }
                    entities.add(obj);
                }
            }
            return entities;
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
