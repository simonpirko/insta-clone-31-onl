package by.tms.instaclone31onl.servlets.base;

import by.tms.instaclone31onl.core.constants.AppConstants;
import by.tms.instaclone31onl.core.constants.ContentTypeConstants;
import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.exceptions.NotImplementServletMethodException;
import by.tms.instaclone31onl.core.models.api.InstaResponse;
import by.tms.instaclone31onl.core.models.entities.User;
import by.tms.instaclone31onl.core.utils.FileUtils;
import by.tms.instaclone31onl.core.utils.JsonConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.tms.instaclone31onl.core.constants.AppConstants.FILES_ORIGIN_PATH;

public class BaseApiServlet extends HttpServlet {
    protected User currentUser;
    private final String FILE_NAME_FORMAT = "%s.%s";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        currentUser = (User) req.getSession().getAttribute("currentUser");
        super.service(req, resp);
    }

    private void response(HttpServletResponse resp, int status, String body, String contentType) throws IOException {
        resp.setContentType(contentType);
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(status);
        try (PrintWriter pw = resp.getWriter()) {
            pw.write(body);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InstaResponse body = new InstaResponse(true, doGetApi(req, resp));
            String response = JsonConverter.serialize(body);
            response(resp, HttpServletResponse.SC_OK, response, ContentTypeConstants.APPLICATION_JSON);
        } catch (NotImplementServletMethodException e) {
            super.doGet(req, resp);
        } catch (Exception e) {
            InstaResponse error = new InstaResponse(false, e.getMessage());
            String errorJson = JsonConverter.serialize(error);
            response(resp, HttpServletResponse.SC_BAD_REQUEST, errorJson, ContentTypeConstants.APPLICATION_JSON);
        }
    }

    protected Object doGetApi(HttpServletRequest req, HttpServletResponse resp) {
        throw new NotImplementServletMethodException("such method not implemented");
    }

    protected Object doDeleteApi(HttpServletRequest req, HttpServletResponse resp) {
        throw new NotImplementServletMethodException("such method not implemented");
    }

    protected Object doPostApi(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        throw new NotImplementServletMethodException("such method not implemented");
    }

    protected Object doPutApi(HttpServletRequest req, HttpServletResponse resp) {
        throw new NotImplementServletMethodException("such method not implemented");
    }

    protected List<String> upload(HttpServletRequest request, String directory) throws IOException {
        List<String> result = new ArrayList<>();
        if (!JakartaServletFileUpload.isMultipartContent(request)) {
            return result;
        }
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload<DiskFileItem, DiskFileItemFactory> fileUpload = new JakartaServletFileUpload<>(factory);
        Path directoriesPath = Path.of(AppConstants.TOMCAT_ROOT, directory);
        List<DiskFileItem> items = fileUpload.parseRequest(request);
        for (DiskFileItem item : items) {
            FileUtils.createDirectoryIfNotExists(directoriesPath);
            String extension = FileUtils.getExtension(item.getName());
            String fileName = FILE_NAME_FORMAT.formatted(UUID.randomUUID().toString(), extension);
            File newFile = new File(directoriesPath.toFile(), fileName);
            item.write(newFile.toPath());

            result.add(Path.of(directory.replace(FILES_ORIGIN_PATH, ServletConstants.FIlE_SERVLET), fileName)
                    .toString().replace("\\", "/"));
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InstaResponse body = new InstaResponse(true, doPostApi(req, resp));
            String response = JsonConverter.serialize(body);
            response(resp, HttpServletResponse.SC_OK, response, ContentTypeConstants.APPLICATION_JSON);
        } catch (NotImplementServletMethodException e) {
            super.doGet(req, resp);
        } catch (Exception e) {
            InstaResponse error = new InstaResponse(false, e.getMessage());
            String errorJson = JsonConverter.serialize(error);
            response(resp, HttpServletResponse.SC_BAD_REQUEST, errorJson, ContentTypeConstants.APPLICATION_JSON);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InstaResponse body = new InstaResponse(true, doDeleteApi(req, resp));
            String response = JsonConverter.serialize(body);
            response(resp, HttpServletResponse.SC_OK, response, ContentTypeConstants.APPLICATION_JSON);
        } catch (NotImplementServletMethodException e) {
            super.doGet(req, resp);
        } catch (Exception e) {
            InstaResponse error = new InstaResponse(false, e.getMessage());
            String errorJson = JsonConverter.serialize(error);
            response(resp, HttpServletResponse.SC_BAD_REQUEST, errorJson, ContentTypeConstants.APPLICATION_JSON);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InstaResponse body = new InstaResponse(true, doPutApi(req, resp));
            String response = JsonConverter.serialize(body);
            response(resp, HttpServletResponse.SC_OK, response, ContentTypeConstants.APPLICATION_JSON);
        } catch (NotImplementServletMethodException e) {
            super.doGet(req, resp);
        } catch (Exception e) {
            InstaResponse error = new InstaResponse(false, e.getMessage());
            String errorJson = JsonConverter.serialize(error);
            response(resp, HttpServletResponse.SC_BAD_REQUEST, errorJson, ContentTypeConstants.APPLICATION_JSON);
        }
    }
}