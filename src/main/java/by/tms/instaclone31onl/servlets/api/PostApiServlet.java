package by.tms.instaclone31onl.servlets.api;

import by.tms.instaclone31onl.core.constants.ServletNameConstants;
import by.tms.instaclone31onl.core.constants.ServletUrlsConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.interfaces.services.TodoService;
import by.tms.instaclone31onl.core.models.entities.PostEntity;
import by.tms.instaclone31onl.core.models.entities.TodoEntity;
import by.tms.instaclone31onl.core.models.request.PostCreateRequest;
import by.tms.instaclone31onl.core.models.request.TodoCreateRequest;
import by.tms.instaclone31onl.core.utils.RequestUtils;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@WebServlet(name = ServletNameConstants.POST_SERVLET, value = ServletUrlsConstants.POST_SERVLET_URL)
public class PostApiServlet extends HttpApiServlet {

    private final ServiceFactory serviceFactory;

    public PostApiServlet() {
        this.serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected Object doGetApi(HttpServletRequest req, HttpServletResponse resp) {
        PostService postService = serviceFactory.getPostService();
        return postService.getAll(getPredicate(req));
    }

    @Override
    protected Object doPostApi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PostCreateRequest postCreateRequest = RequestUtils.getRequestBody(req, PostCreateRequest.class);
        PostService postService = this.serviceFactory.getPostService();
        return postService.add(postCreateRequest);
    }

    private Predicate<PostEntity> getPredicate(HttpServletRequest req) {
        Predicate<PostEntity> predicate = x -> true;

//        if (req.getParameter("id") != null) {
//            UUID id = UUID.fromString(req.getParameter("id"));
//            predicate = predicate.and(x -> x.getId().equals(id));
//        }
//
//        if (req.getParameter("search") != null) {
//            String search = req.getParameter("search");
//            predicate = predicate.and(x -> x.getTitle().startsWith(search) || x.getDescription().endsWith(search));
//        }
//
//        if (req.getParameter("ownerId") != null) {
//            UUID ownedId = UUID.fromString(req.getParameter("ownerId"));
//            predicate = predicate.and(x -> x.getOwnerId() == ownedId);
//        }
        return predicate;
    }
}
