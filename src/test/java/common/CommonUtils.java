package common;

public class CommonUtils {
    public static final String BASE_URL = ConfigUtils.getDotenv().get("url");
    public static final String PORT = ConfigUtils.getDotenv().get("port");
    public static final String COMMON_URL = String.format("%s:%s",BASE_URL,PORT);
    public static final String ADMIN_LOGIN_PATH = "/admin/login";
    public static final String CREATE_PRODUCT_API = "http://localhost:3000/api/products";
    public static final String DELETE_PRODUCT_API = "http://localhost:3000/api/products/%s";
}
