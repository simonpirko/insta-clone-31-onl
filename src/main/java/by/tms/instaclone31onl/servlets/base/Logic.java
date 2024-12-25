package by.tms.instaclone31onl.servlets.base;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public class Logic {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public Logic(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

}
