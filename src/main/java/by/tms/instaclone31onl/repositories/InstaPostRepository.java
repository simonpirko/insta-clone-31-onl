package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.Post;

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
}
