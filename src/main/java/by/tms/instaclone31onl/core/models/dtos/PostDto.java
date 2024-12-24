package by.tms.instaclone31onl.core.models.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PostDto(UUID id, UserShortDto user,
                      LocalDateTime date, String description,
                      List<String> images, List<ReactionDto> likes,
                      List<CommentDto> comments) {
}