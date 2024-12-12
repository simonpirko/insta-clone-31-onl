package by.tms.instaclone31onl.core.models.request;

import java.util.UUID;

public record TodoCreateRequest(String title, String description, UUID ownerId){}
