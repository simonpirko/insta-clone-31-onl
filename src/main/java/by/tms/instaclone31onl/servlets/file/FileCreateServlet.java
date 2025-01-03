package by.tms.instaclone31onl.servlets.file;

import by.tms.instaclone31onl.servlets.base.BaseApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.*;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static by.tms.instaclone31onl.core.constants.AppConstants.IMAGES_POST_DESTINATION_FORMAT_PATH;


//import static sun.font.CreatedFontTracker.MAX_FILE_SIZE;

@WebServlet("/filecreate")
public class FileCreateServlet extends BaseApiServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/fileupload.jsp").forward(request, response);
    }

    @Override
    protected Object doPostApi(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String username ="username"; // set current user or send login

        List<String> paths = upload(req, IMAGES_POST_DESTINATION_FORMAT_PATH.formatted(username));
        // IMAGES_PROFILE_DESTINATION_FORMAT_PATH для профиля
        return paths;
    }
}
