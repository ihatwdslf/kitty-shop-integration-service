package kittyshop.integration.service.api.config;

// List of all available controller routes
public abstract class ControllerRoutes {

    // Main related Controllers
    public static final String HEALTH_CHECK = "/ping";

    // Users related Controllers
    public static final String USER_GET = "/v1/users/{id}";

    public static final String USERS_GET = "/v1/users";
    public static final String USERS_GET_ROLES = "/v1/users/roles";

    public static final String USER_CREATE = "/v1/users";
    public static final String USER_DELETE = "/v1/users/{id}";
    public static final String USER_UPDATE = "/v1/users/{id}";

    public static final String AUTH_REGISTRATION = "/v1/auth/registration";
    public static final String AUTH_LOGIN = "/v1/auth/login";
    public static final String AUTH_LOGOUT = "/v1/auth/logout";
    public static final String AUTH_ME = "/v1/auth/me";
}
