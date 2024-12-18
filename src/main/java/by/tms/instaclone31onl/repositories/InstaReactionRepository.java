package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.repositories.ReactionRepository;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.Reaction;

public class InstaReactionRepository
        extends BaseRepository<Reaction>
        implements ReactionRepository
{
    public InstaReactionRepository(CsvTable<Reaction> csvTable) {
        super(csvTable);
    }

    @Override
    protected Reaction mapper(String[] line) {
        return Reaction.fromLine(line);
    }
}
