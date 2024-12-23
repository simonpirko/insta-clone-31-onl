package by.tms.instaclone31onl.servlets.base;

import by.tms.instaclone31onl.core.constants.ContentTypeConstants;
import by.tms.instaclone31onl.core.exceptions.NotImplementServletMethodException;
import by.tms.instaclone31onl.core.models.api.InstaResponse;
import by.tms.instaclone31onl.core.utils.JsonConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class BaseApiServlet extends HttpServlet {

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
        }
        catch (NotImplementServletMethodException e) {
            super.doGet(req, resp);
        }
        catch (Exception e) {
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
    protected Object doPostApi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        throw new NotImplementServletMethodException("such method not implemented");
    }
    protected Object doPutApi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        throw new NotImplementServletMethodException("such method not implemented");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InstaResponse body = new InstaResponse(true, doPostApi(req, resp));
            String response = JsonConverter.serialize(body);
            response(resp, HttpServletResponse.SC_OK, response, ContentTypeConstants.APPLICATION_JSON);
        }
        catch (NotImplementServletMethodException e) {
            super.doGet(req, resp);
        }
        catch (Exception e) {
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
        }
        catch (NotImplementServletMethodException e) {
            super.doGet(req, resp);
        }
        catch (Exception e) {
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
        }
        catch (NotImplementServletMethodException e) {
            super.doGet(req, resp);
        }
        catch (Exception e) {
            InstaResponse error = new InstaResponse(false, e.getMessage());
            String errorJson = JsonConverter.serialize(error);
            response(resp, HttpServletResponse.SC_BAD_REQUEST, errorJson, ContentTypeConstants.APPLICATION_JSON);
        }
    }
}