package by.tms.instaclone31onl.core.interfaces.repositories;

import by.tms.instaclone31onl.core.models.entities.Post;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface PostRepository extends Repository<Post, UUID> {
    List<Post> getPagedList(Predicate<Post> predicate, int start, int count);
}
