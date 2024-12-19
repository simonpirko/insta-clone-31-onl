package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.PostRepository;
import by.tms.instaclone31onl.core.interfaces.services.PostService;

public class InstaPostService implements PostService {
    private final PostRepository postRepository;
    public InstaPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
