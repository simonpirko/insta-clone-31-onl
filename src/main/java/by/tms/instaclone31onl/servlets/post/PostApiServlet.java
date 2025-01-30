package by.tms.instaclone31onl.servlets.post;

import by.tms.instaclone31onl.core.constants.PageConstants;
import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.models.entities.Post;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        int start = Optional.ofNullable(req.getParameter("start")).isPresent()
                ? Integer.parseInt(req.getParameter("start"))
                : PageConstants.PAGE_POST_START;
        int count = Optional.ofNullable(req.getParameter("count")).isPresent()
                ? Integer.parseInt(req.getParameter("count"))
                : PageConstants.PAGE_POST_COUNT;
        return postService.getPagedList(getPredicate(req), start, count);
    }

    private Predicate<Post> getPredicate(HttpServletRequest request) {
        Predicate<Post> predicate = x -> true;
        String id = request.getParameter("userId");
        boolean isFriends = Boolean.parseBoolean(request.getParameter("friends"));

        if (id != null) {
            UUID uuid = UUID.fromString(id);
            predicate = predicate.and(post -> post.getUserId().equals(uuid));
        }
        if(isFriends)
        {
            List<UUID> friendIds = currentUser.getFriendIds();
            predicate = predicate.and(post -> friendIds.contains(post.getUserId()));
        }
        //TODO set filters
        return predicate;
    }
}

