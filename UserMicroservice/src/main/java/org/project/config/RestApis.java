package org.project.config;

public class RestApis {
    public static final String DEVELOPER = "/dev";
    public static final String TEST = "/dev";
    public static final String RELEASE = "/prod";
    public static final String VERSIONS = "/v1";

    public static final String USERPROFILE = DEVELOPER + VERSIONS + "/user-profile";
    public static final String AUTH = DEVELOPER + VERSIONS + "/auth";
    public static final String VERIFY_TOKEN = "/verifyToken";
    public static final String BASE_URL = "http://localhost:9099";

    public static final String CREATE_USER = "/create-user";
    public static final String GET_ALL = "/get-all";
    public static final String BY_ID = "/{id}";
    public static final String UPPER_NAME = "/upper-name";
    public static final String ACTIVATE_USER = "/user-activate/{token}";


}
