package by.tms.instaclone31onl.core.models.dtos;

import java.util.List;
import java.util.UUID;

public record UserShortDto (UUID id, String nickname, List<String> photos) {
}