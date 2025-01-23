package by.tms.instaclone31onl.core.models.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDto(UUID id, UserShortDto author, String text, LocalDateTime date) {
}
