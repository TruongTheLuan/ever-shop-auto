package pages;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;

import java.util.List;

public class EditProductPage extends CommonPage{
    public String pageHeader = "Editing %s";
    private static String DELETE_PRODUCT_API = "http://localhost:3000/api/products/%s";
    public EditProductPage(Page page) {
        super(page);
    }

    public void detectProductByApi(String productName){
        verifyPageWithHeader(String.format("Editing %s", productName));
        String pageUrl = page.url();
        List<String> urlElements = List.of(pageUrl.split("/"));
        String productId = urlElements.get(urlElements.size()-1);
        APIResponse response = page.request().delete(String.format(DELETE_PRODUCT_API, productId));
    }
}
