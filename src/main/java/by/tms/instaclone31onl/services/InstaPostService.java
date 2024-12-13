package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.enums.PostStatus;
import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.models.entities.PostEntity;
import by.tms.instaclone31onl.core.models.request.PostCreateRequest;
import by.tms.instaclone31onl.factories.InstaRepositoryFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class InstaPostService implements PostService {

    private final PostRepository postRepository;

    public InstaPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<UUID> add(PostCreateRequest request) {
        PostEntity entity = new PostEntity(null,
                request.userId(),
                request.title(),
                request.body(),
                PostStatus.DRAW,
                null,
                null,
                LocalDate.now(),
                LocalDate.now(),
                request.images());
        return postRepository.insert(List.of(entity));
    }

    @Override
    public List<PostEntity> getAll(Predicate<PostEntity> predicate) {
        return postRepository.getAll(predicate);
    }

    @Override
    public PostEntity getById(UUID id) {
        return getById(id);
    }
}
