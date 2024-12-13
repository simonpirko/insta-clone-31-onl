package by.tms.instaclone31onl.core.models.request;

import java.util.List;
import java.util.UUID;

public record PostCreateRequest(UUID userId, String title, String body, List<String> images){

}
