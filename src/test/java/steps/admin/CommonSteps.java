package steps.admin;

import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class CommonSteps {
    private CommonPage commonPage = new CommonPage(page);
    @When("I input {string} field with value {string}")
    public void inputText(String label, String value) {
        commonPage.inputText(label, value);
    }

    @When("I select menu item {string}")
    public void selectItem(String label){
        commonPage.selectItemByLabel(label);
    }

    @Then("I should see {string} page with {string}")
    public void verifyPageWithHeader(String pageName, String header) {
        commonPage.verifyPageWithHeader(header);
    }

    @Then("I should see {string} page")
    public void verifyPageWithTitle(String pageTitle) {
        commonPage.verifyPageTitle(pageTitle);
    }

    @And("I select option {string} of radio {string}")
    public void selectRadioOption(String option, String radioLabel) {
        commonPage.selectRadioOption(option, radioLabel);
    }

    @And("I click on {string} button")
    public void clickButton(String label) {
        commonPage.clickButtonByLabel(label);
    }
}
