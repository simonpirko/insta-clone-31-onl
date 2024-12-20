package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.dtos.PostDto;
import by.tms.instaclone31onl.core.models.entities.Post;

import java.util.List;
import java.util.function.Predicate;

public interface PostService {

    List<PostDto> getAll(Predicate<Post> predicate);
}
