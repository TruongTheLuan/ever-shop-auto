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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static common.CommonUtils.DELETE_PRODUCT_API;
import static data.productTestData.productData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Hooks {
    public static Playwright playwright;
    public static Browser browser;

    // New instance for each test method.
    public static BrowserContext context;
    public static Page page;
    private static String CREATE_PRODUCT_API = "http://localhost:3000/api/products";
    public static String STUB_RESPONSE_CREATE_PRODUCT = "1734496800501-1765871E-ACA4-42B7-8371-913594B92955";
    public static Set<String> createProductIds = new HashSet<>();

    @BeforeAll
    public static void beforeAll(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }
    @AfterAll
    public static void afterAll(){
        playwright.close();
    }
    @Before(order = 1)
    public static void beforeEach(){
        context = browser.newContext();
        page = context.newPage();
        page.route("**", handler -> {
            String pageUrl = handler.request().url();
            String method = handler.request().method();
            System.out.println("URL: " + pageUrl);
            System.out.println("method: " + method);
            if(pageUrl.contains("/admin/products/edit")){
                handleNewProduct(pageUrl, handler);
            }
            else{
                handler.resume();
            }
        });
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
    @Before("@create_product_with_invalid_uuid")
    public static void createProductWithInvalidUuid(){
        page.route("**", handler -> {
            String pageUrl = handler.request().url();
            String method = handler.request().method();
            System.out.println("URL: " + pageUrl);
            System.out.println("method: " + method);
            if("POST".equals(method) && pageUrl.endsWith("/api/products")){
                handler.fulfill(new Route.FulfillOptions()
                        .setStatus(500)
                        .setContentType("application/json")
                        .setBody(STUB_RESPONSE_CREATE_PRODUCT));
            }
            else{
                handler.resume();
            }
        });
    }
    private static void handleNewProduct(String pageUrl, Route handler){
        List<String> urlElements = List.of(pageUrl.split("/"));
        String productId = urlElements.get(urlElements.size()-1);
        createProductIds.add(productId);
        handler.resume();
    }
    @After
    public static void afterEach(){
        createProductIds.forEach(id -> {
            page.request().delete(String.format(DELETE_PRODUCT_API, id));
        });
        context.close();
    }
}
