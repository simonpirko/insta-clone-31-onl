package by.tms.instaclone31onl.servlets.post;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.models.entities.Post;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.function.Predicate;

@WebServlet(name = ServletConstants.POST_API_SERVLET_NAME, value = ServletConstants.POST_API_SERVLET)
public class PostApiServlet extends BaseApiServlet {

    private final ServiceFactory serviceFactory;

    public PostApiServlet() {
        this.serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected Object doGetApi(HttpServletRequest req, HttpServletResponse resp) {
        PostService postService = serviceFactory.getPostService();
        return postService.getAll(getPredicate(req));
    }


    private Predicate<Post> getPredicate(HttpServletRequest request) {
        Predicate<Post> predicate = x -> true;

        //TODO set filters

        return predicate;
    }
}

