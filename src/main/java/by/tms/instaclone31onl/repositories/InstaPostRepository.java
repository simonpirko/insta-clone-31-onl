package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.constants.DateConstants;
import by.tms.instaclone31onl.core.enums.PostStatus;
import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.PostEntity;
import by.tms.instaclone31onl.core.utils.JsonConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class InstaPostRepository
        extends InstaRepository<PostEntity>
        implements PostRepository {
    public InstaPostRepository(CsvTable<PostEntity> table) {
        super(table);
    }

    @Override
    protected PostEntity mapper(String[] line) {
        UUID id = UUID.fromString(line[0]);
        UUID userId = UUID.fromString(line[1]);
        String title = line[2];
        String body = line[3];
        PostStatus status = PostStatus.getStatusByValue((Integer.valueOf(line[4])));
        List<UUID> commentIds = (List<UUID>) JsonConverter.deserialize(line[5], List.class);
        List<UUID> reactionIds = (List<UUID>) JsonConverter.deserialize(line[6], List.class);
        LocalDate createdAt = LocalDate.parse(line[7], DateTimeFormatter.ofPattern(DateConstants.DATE_FORMAT));
        LocalDate updatedAt = LocalDate.parse(line[8], DateTimeFormatter.ofPattern(DateConstants.DATE_FORMAT));
        List<String> images = (List<String>) JsonConverter.deserialize(line[9], List.class);

        return new PostEntity(id, userId, title, body, status, commentIds, reactionIds, createdAt, updatedAt, images);
    }
}
