package by.tms.instaclone31onl.core.models.request;

import java.util.UUID;

public record TodoUpdateRequest(UUID id, String title, String description, UUID ownerId){}
