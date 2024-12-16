package steps.admin;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProductPage;

import static steps.Hooks.page;

public class ProductPageSteps {
    ProductPage productPage = new ProductPage(page);
    @When("I select product by name {string}")
    public void selectProductByName(String name){
        productPage.selectItemByLabel(name);
    }
}
