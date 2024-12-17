package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.Comment;

public class InstaCommentRepository
        extends BaseRepository<Comment>
        implements CommentRepository
{
    public InstaCommentRepository(CsvTable<Comment> csvTable) {
        super(csvTable);
    }

    @Override
    protected Comment mapper(String[] line) {
        return Comment.fromLine(line);
    }
}
