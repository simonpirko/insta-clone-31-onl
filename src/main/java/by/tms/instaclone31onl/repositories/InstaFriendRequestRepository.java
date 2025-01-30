package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.repositories.CommentRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.FriendRequestRepository;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.Comment;
import by.tms.instaclone31onl.core.models.entities.FriendRequest;

public class InstaFriendRequestRepository   extends BaseRepository<FriendRequest>
        implements FriendRequestRepository
{
    public InstaFriendRequestRepository(CsvTable<FriendRequest> csvTable) {
        super(csvTable);
    }

    @Override
    protected FriendRequest mapper(String[] line) {
        return FriendRequest.fromLine(line);
    }
}
