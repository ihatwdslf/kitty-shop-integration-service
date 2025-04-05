package kittyshop.integration.service.api.config;

// List of all available controller routes
public abstract class ControllerRoutes {

    // Main related Controllers
    public static final String HEALTH_CHECK = "/api/v1/ping";

    // Users related Controllers
    public static final String USER_GET = "/api/v1/users/{id}";

    public static final String USERS_GET = "/api/v1/users";
    public static final String USERS_GET_ROLES = "/api/v1/users/roles";

    public static final String USER_DELETE = "/api/v1/users/{id}";
    public static final String USER_UPDATE = "/api/v1/users/{id}";

    // Auth related Controllers
    public static final String AUTH_REGISTRATION = "/api/v1/auth/registration";
    public static final String AUTH_LOGIN = "/api/v1/auth/login";
    public static final String AUTH_LOGOUT = "/api/v1/auth/logout";
    public static final String AUTH_ME = "/api/v1/auth/me";

    // Brands related Controllers
    public static final String BRANDS_GET = "/api/v1/brands";
    public static final String BRAND_CREATE = "/api/v1/brands";
    public static final String BRAND_GET = "/api/v1/brands/{id}";
    public static final String BRAND_DELETE = "/api/v1/brands/{id}";
    public static final String BRAND_UPDATE = "/api/v1/brands/{id}";

    // Categories related Controllers
    public static final String CATEGORIES_GET = "/api/v1/categories";
    public static final String CATEGORY_CREATE = "/api/v1/categories";
    public static final String CATEGORY_GET = "/api/v1/categories/{id}";
    public static final String CATEGORY_DELETE = "/api/v1/categories/{id}";
    public static final String CATEGORY_UPDATE = "/api/v1/categories/{id}";
    public static final String CATEGORY_NESTED_GET ="/api/v1/categories/{id}/nested";

    // Products related Controllers
    public static final String PRODUCTS_GET = "/api/v1/products";
    public static final String PRODUCT_CREATE = "/api/v1/products";
    public static final String PRODUCT_GET = "/api/v1/products/{id}";
    public static final String PRODUCT_DELETE = "/api/v1/products/{id}";
    public static final String PRODUCT_UPDATE = "/api/v1/products/{id}";
    public static final String PRODUCT_TOTALS = "/api/v1/products/totals";

    // Order related Controllers
    public static final String ORDERS_GET = "/api/v1/orders";
    public static final String ORDER_CREATE = "/api/v1/orders";
    public static final String ORDER_GET = "/api/v1/orders/{id}";
    public static final String ORDER_DELETE = "/api/v1/orders/{id}";
    public static final String ORDER_UPDATE = "/api/v1/orders/{id}";

    // Order item related Controllers
    public static final String ORDER_ITEM_ADD = "/api/v1/orders/{orderId}/items";
    public static final String ORDER_ITEMS_GET = "/api/v1/orders/{orderId}/items";
    public static final String ORDER_ITEM_GET = "/api/v1/orders/{orderId}/items/{productId}";
    public static final String ORDER_ITEM_REMOVE = "/api/v1/orders/{orderId}/items/{productId}";
    public static final String ORDER_ITEM_UPDATE = "/api/v1/orders/{orderId}/items/{productId}";
}
