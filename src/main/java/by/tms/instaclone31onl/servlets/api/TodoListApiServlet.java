package by.tms.instaclone31onl.servlets.api;

import by.tms.instaclone31onl.core.constants.ServletNameConstants;
import by.tms.instaclone31onl.core.constants.ServletUrlsConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.TodoService;
import by.tms.instaclone31onl.core.models.entities.TodoEntity;
import by.tms.instaclone31onl.core.models.request.TodoCreateRequest;
import by.tms.instaclone31onl.core.models.request.TodoUpdateRequest;
import by.tms.instaclone31onl.core.utils.RequestUtils;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@WebServlet(name = ServletNameConstants.TODO_SERVLET, value = ServletUrlsConstants.GET_TODO_SERVLET)
public class TodoListApiServlet extends HttpApiServlet {

    private final ServiceFactory serviceFactory;

    public TodoListApiServlet() {
        this.serviceFactory = InstaServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoService todoService = serviceFactory.getTodoService();
        List<TodoEntity> enities = todoService.getAll(getPredicate(req));
        req.setAttribute("entities", enities);
        getServletContext().getRequestDispatcher(ServletUrlsConstants.TODO_URL_JSP).forward(req, resp);
    }

//        @Override
//    protected Object doGetApi(HttpServletRequest req, HttpServletResponse resp) {
//        TodoService todoService = serviceFactory.getTodoService();
//        return todoService.getAll(getPredicate(req));
//    }

    @Override
    protected Object doPostApi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TodoCreateRequest todoCreate = RequestUtils.getRequestBody(req, TodoCreateRequest.class);
        TodoService todoService = this.serviceFactory.getTodoService();
        return todoService.add(List.of(todoCreate));
    }

    @Override
    protected Object doDeleteApi(HttpServletRequest req, HttpServletResponse resp) {
        TodoService todoService = this.serviceFactory.getTodoService();
        return todoService.delete(getPredicate(req));
    }

    @Override
    protected Object doPutApi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TodoUpdateRequest todoUpdate = RequestUtils.getRequestBody(req, TodoUpdateRequest.class);
        TodoService todoService = this.serviceFactory.getTodoService();
        return todoService.update(todoUpdate);
    }

    private Predicate<TodoEntity> getPredicate(HttpServletRequest req) {
        Predicate<TodoEntity> predicate = x->true;

        if (req.getParameter("id") != null) {
            UUID id = UUID.fromString(req.getParameter("id"));
            predicate = predicate.and(x -> x.getId().equals(id));
        }

        if (req.getParameter("search") != null) {
            String search = req.getParameter("search");
            predicate = predicate.and(x -> x.getTitle().startsWith(search) || x.getDescription().endsWith(search));
        }

        if (req.getParameter("ownerId") != null) {
            UUID ownedId = UUID.fromString(req.getParameter("ownerId"));
            predicate = predicate.and(x -> x.getOwnerId() == ownedId);
        }
        return predicate;
    }
}
