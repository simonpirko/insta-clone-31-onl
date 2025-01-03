package by.tms.instaclone31onl.servlets.file;

import by.tms.instaclone31onl.core.constants.AppConstants;
import by.tms.instaclone31onl.core.constants.ServletConstants;
import by.tms.instaclone31onl.core.interfaces.factories.ServiceFactory;
import by.tms.instaclone31onl.core.interfaces.services.FileService;
import by.tms.instaclone31onl.core.utils.FileUtils;
import by.tms.instaclone31onl.factories.InstaServiceFactory;
import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = ServletConstants.FILE_SERVLET_URL_PATTERN)
public class FileServlet extends BaseApiServlet{

    private final ServiceFactory serviceFactory;
    public FileServlet() {
        this.serviceFactory = InstaServiceFactory.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getRequestURI().replace(ServletConstants.FIlE_SERVLET, AppConstants.FILES_ORIGIN_PATH).trim();
        if(path.isBlank() || path.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        FileService fileService = serviceFactory.getFileService();
        byte[] image = fileService.getFileBytes(path);

        if(image == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getOutputStream().write(image);
    }
}
