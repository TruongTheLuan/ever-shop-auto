package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.net.URL;
import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NewProductPage extends CommonPage{
    public String pageUrl = "/admin/products/new";
    public String pageHeader = "Create A New Product";

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

    public boolean shouldBeOnPage(){
        return true;
    }
}
