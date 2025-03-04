package org.project.config;

public class RestApis {

    public static final String DEVELOPER = "/dev";
    public static final String TEST = "/dev";
    public static final String RELEASE = "/prod";
    public static final String VERSIONS = "/v1";

    public static final String AUTHSERVICE = DEVELOPER + VERSIONS + "/auth";

    public static final String REGISTER = "/register";
    public static final String AUTHORIZE = "/authorize";
    public static final String ACTIVATE = "/register/{token}/activate";
    public static final String REGISTERED = "/hasRegistered";
    public static final String LOGIN = "/login";
    public static final String VERIFY_TOKEN = "/verifyToken";
    public static final String LOGOUT = "/logout";

    public static final String CREATE_USER = "/create-user";
    public static final String ACTIVATE_USER = "/user-activate/{token}";
    public static final String USERPROFILE = DEVELOPER + VERSIONS + "/user-profile";
    public static final String BASE_URL = "http://localhost:9091";
    public static final String TOKEN = "/token";
    public static final String GETALL = "/get-all";
}
