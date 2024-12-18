package pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.RequestOptions;
import data.productTestData;

import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import model.Product;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static steps.Hooks.playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static data.productTestData.productData;

public class NewProductPage extends CommonPage{
    public String pageUrl = "/admin/products/new";
    public String pageHeader = "Create A New Product";
    public String uuid;
    private static String CREATE_PRODUCT_API = "http://localhost:3000/api/products";

    public NewProductPage(Page page) {
        super(page);
    }

    public void selectCategory(String categoryItem) {
        String selectCategoryXpath = "//a[text()[normalize-space()='Select category']]";
        Locator selectCategoryLocator = page.locator(selectCategoryXpath);
        selectCategoryLocator.click();
        String categoryItemXpath = String.format("//a[text()[normalize-space()='%s']]",categoryItem);
        Locator categoryItemLocator = page.locator(categoryItemXpath);
        categoryItemLocator.click();
    }

    public void selectAttribute(String label, String option) {
        String selectCategoryXpath = String.format("//tr[.//td[normalize-space(.//text())='%s']]//select", label);
        Locator selectCategoryLocator = page.locator(selectCategoryXpath);
        selectCategoryLocator.selectOption(option);
    }

    public void inputProductDescription(String description) {
        String layoutXpath = "//div[./label[normalize-space()='Description']]//a[1]";
        page.locator(layoutXpath).click();
        String descriptionAreaXpath = "//div[./label[normalize-space()='Description']]//div[@data-placeholder-active='Type / to see the available blocks']";
        page.locator(descriptionAreaXpath).fill(description);
    }

    public void setImageOnNewProduct(String imagePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL inputStream = classLoader.getResource(imagePath);
        String buttonUploadXpath = "//div[@id='images']//input";
        Locator buttonUploadLocator = page.locator(buttonUploadXpath);
        buttonUploadLocator.setInputFiles(Path.of(inputStream.getPath()));
    }

    public void verifyNotification(String notificationContent) {
        String notificationXpath = String.format("//div[@role='alert' and text()[normalize-space()='%s']]", notificationContent);
        Locator notificationLocator = page.locator(notificationXpath);
        assertThat(notificationLocator).isVisible();
    }

    public void inputRandomSKU() {
        String sku = String.valueOf(System.currentTimeMillis());
        inputText("SKU", sku);
    }

    public void createNewProductByApi() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(productData, new TypeReference<Map<String, Object>>(){});
        APIResponse apiResponse = page.request().post(CREATE_PRODUCT_API, RequestOptions.create().setData(data));
        String respone = new String(apiResponse.body(), StandardCharsets.UTF_8);
        System.out.println(respone);
        //Save result from response => Delete product by ID/UUID
        //new String(response.body()) => convert byte[] to string
        assertTrue(apiResponse.ok());
    }

    public boolean shouldBeOnPage(){
        return true;
    }
}
