package by.tms.instaclone31onl.core.constants;

import java.util.List;

public final class ServletConstants {

    public static final String LOGIN_SERVLET = "/login";
    public static final String REGISTRATION_SERVLET = "/registration";
    public static final String POST_SERVLET = "/post";
    public static final String POST_SERVLET_NAME = "POST-SERVLET";
    public static final String POST_API_SERVLET = "/api/post";
    public static final String POST_API_SERVLET_NAME = "POST-API-SERVLET";
    public static final String REACTION_SERVLET_NAME = "REACTION-API-SERVLET";
    public static final String REACTION_API_SERVLET = "/api/reaction";
    public static final String PROFILE_SERVLET = "/profile";
    public static final String EDIT_PROFILE_SERVLET = "/edit";
    public static final String FIlE_SERVLET = "/file";
    public static final String FILE_SERVLET_URL_PATTERN =  "/file/*";
    public static final String LOGOUT_SERVLET = "/logout";
    public static final String FILL_DATA_SERVLET = "/fill";
    public static final List<String> UNAUTHORIZED_URLS = List.of(LOGIN_SERVLET, REGISTRATION_SERVLET, FILL_DATA_SERVLET);
    public static final String COMMENT_SERVLET_NAME = "COMMENT-API-SERVLET";
    public static final String COMMENT_API_SERVLET = "/api/comment";
    public static final String SEARCH_SERVLET = "/search";
    public static final String FRIEND_SEARCH_SERVLET = "/friendsearch";
    public static final String FRIENDS_SERVLET = "/friends";
    public static final String FRIEND_REQUEST_SERVLET = "/request";

}
