package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import data.productTestData;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import java.util.List;
import java.util.Map;
import static data.productTestData.productData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Hooks {
    public static Playwright playwright;
    public static Browser browser;

    // New instance for each test method.
    public static BrowserContext context;
    public static Page page;
    private static String CREATE_PRODUCT_API = "http://localhost:3000/api/products";

    @BeforeAll
    public static void beforeAll(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }
    @AfterAll
    public static void afterAll(){
        playwright.close();
    }
    @Before
    public static void beforeEach(){
        context = browser.newContext();
        page = context.newPage();
    }
    @Before("@login_with_error_500")
    public static void loginWithError500(){
        page.route("**", handler -> {
            String pageUrl = handler.request().url();
            String method = handler.request().method();
            System.out.println("URL: " + pageUrl);
            System.out.println("method: " + method);
            if("POST".equals(method) && pageUrl.endsWith("/admin/user/login")){
                handler.fulfill(new Route.FulfillOptions()
                        .setStatus(500)
                        .setContentType("text/plain")
                        .setBody("Server error!"));
            }
            else{
                handler.resume();
            }
        });
    }
    @After
    public static void afterEach(){
        context.close();
    }
}
