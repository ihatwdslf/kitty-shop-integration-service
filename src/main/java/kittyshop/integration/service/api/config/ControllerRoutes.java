package kittyshop.integration.service.api.config;

// List of all available controller routes
public abstract class ControllerRoutes {

    // Main related Controllers
    public static final String HEALTH_CHECK = "/api/v1/ping";

    // Users related Controllers
    public static final String USER_GET = "/api/v1/users/{id}";

    public static final String USERS_GET = "/api/v1/users";
    public static final String USERS_GET_ROLES = "/api/v1/users/roles";

    public static final String USER_CREATE = "/api/v1/users";
    public static final String USER_DELETE = "/api/v1/users/{id}";
    public static final String USER_UPDATE = "/api/v1/users/{id}";

    public static final String AUTH_REGISTRATION = "/api/v1/auth/registration";
    public static final String AUTH_LOGIN = "/api/v1/auth/login";
    public static final String AUTH_LOGOUT = "/api/v1/auth/logout";
    public static final String AUTH_ME = "/api/v1/auth/me";
}
