package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.entities.PostEntity;
import by.tms.instaclone31onl.core.models.request.PostCreateRequest;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface PostService {
    List<UUID> add(PostCreateRequest request);
    List<PostEntity> getAll(Predicate<PostEntity> predicate);
    PostEntity getById(UUID id);
}
