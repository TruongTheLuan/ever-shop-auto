package steps.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EditProductPage;
import pages.NewProductPage;

import java.net.URL;
import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class NewProductPageSteps {
    NewProductPage newProductPage = new NewProductPage(page);
    EditProductPage editProductPage = new EditProductPage(page);
    @And("I select category {string} on New Product page")
    public void selectCategory(String categoryItem) {
        newProductPage.selectCategory(categoryItem);
    }

    @And("I input product description on new product page")
    public void inputProductDescription(String description) {
        newProductPage.inputProductDescription(description);
    }

    @And("I set image {string} on new product page")
    public void setImageOnNewProduct(String imagePath) {
        newProductPage.setImageOnNewProduct(imagePath);
    }

    @Then("I should see notification is {string}")
    public void verifyNotification(String notificationContent) {
        newProductPage.verifyNotification(notificationContent);
    }

    @Then("I wait for {int} ms")
    public void iWaitForMs(int ms) throws InterruptedException {
        Thread.sleep(ms);
    }

    @And("I select attribute {string} with value {string}")
    public void selectOptionOfSelect(String attribute, String value) {
        newProductPage.selectAttribute(attribute, value);
    }

    @And("I delete product {string}")
    public void deleteProduct(String productName) {
        editProductPage.detectProductByApi(productName);
    }

    @And("I input a random SKU")
    public void inputRandomSKU() {
        newProductPage.inputRandomSKU();
    }

    @When("I create a product by api")
    public void iCreateAProductByApi() throws JsonProcessingException {
        newProductPage.createNewProductByApi();
    }
}
