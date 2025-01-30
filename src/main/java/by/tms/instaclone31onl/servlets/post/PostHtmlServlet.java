package by.tms.instaclone31onl.servlets.post;

import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.enums.PostStatus;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.PostService;
import by.tms.instaclone31onl.core.models.requests.PostCreateRequest;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

import static by.tms.instaclone31onl.core.constants.AppConstants.IMAGES_POST_DESTINATION_FORMAT_PATH;

@WebServlet(name = ServletConstants.POST_SERVLET_NAME, value = ServletConstants.POST_SERVLET)
public class PostHtmlServlet extends BaseApiServlet {
    private final ServiceFactory serviceFactory;
    public PostHtmlServlet() {
        serviceFactory = InstaServiceFactory.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String noPostsMessage = "Нет публикаций для отображения";
        String userId = request.getParameter("userId");
        if(userId != null)
            noPostsMessage = "У данного пользователя нет публикаций";
        if (Boolean.parseBoolean(request.getParameter("friends")))
            noPostsMessage = "Ваши друзья пока не сделали публикаций";
        request.setAttribute("noPostsMessage", noPostsMessage);
        request.getRequestDispatcher("/pages/posts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostService postService = serviceFactory.getPostService();
        PostCreateRequest postCreateRequest = getPostCreateRequest(request);
        request.getRequestDispatcher("/pages/posts.jsp").include(request, response);

    }

    private PostCreateRequest getPostCreateRequest(HttpServletRequest request) throws IOException {
        HashMap<String, Object> multiPartContent = getMultiPartContent(request, IMAGES_POST_DESTINATION_FORMAT_PATH.formatted(currentUser.getLogin()));
        PostCreateRequest postCreate = new PostCreateRequest();
        if(multiPartContent.containsKey("description")) {
            postCreate.setDescription(multiPartContent.get("description").toString());
        }
        if(multiPartContent.containsKey("file")){
            postCreate.setPhotos((List<String>) multiPartContent.get("file"));
        }
        postCreate.setStatus(PostStatus.PUBLISHED);
        postCreate.setUserId(currentUser.getId());
        return postCreate;
    }
}
