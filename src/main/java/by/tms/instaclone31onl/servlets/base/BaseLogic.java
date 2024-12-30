package by.tms.instaclone31onl.servlets.base;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public class BaseLogic {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public BaseLogic(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

}
