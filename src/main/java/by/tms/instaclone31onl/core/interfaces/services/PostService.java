package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.dtos.PostDto;
import by.tms.instaclone31onl.core.models.entities.Post;
import by.tms.instaclone31onl.core.models.requests.PostCreateRequest;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface PostService {

    List<PostDto> getPagedList(Predicate<Post> predicate, int start, int count);
    List<UUID> insert(PostCreateRequest request);
}
