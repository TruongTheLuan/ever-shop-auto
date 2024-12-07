package steps.admin;

import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static steps.Hooks.page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminLoginSteps {
    @Given("I open admin login page")
    public void openAdminLoginPage(){
        page.navigate("http://localhost:3000/admin/login");
        String buttonSignInXpath = "//button[normalize-space(.//text())='SIGN IN']";
        Locator buttonSignInLocator = page.locator(buttonSignInXpath);
        assertThat(buttonSignInLocator).isVisible();
    }

    @When("I input {string} field with value {string}")
    public void inputTest(String label, String value) {
        String inputFieldXpath = String.format("//div[./label[normalize-space()='%s']]//input", label);
        Locator inputFieldLocator = page.locator(inputFieldXpath);
        inputFieldLocator.fill(value);
    }

    @And("I click on {string} button")
    public void clickButton(String label) {
        String loginButtonXpath = String.format("//button[normalize-space(.//text())='%s']", label);
        Locator loginButtonLocator = page.locator(loginButtonXpath);
        loginButtonLocator.click();
    }

    @Then("I should see dashboard page")
    public void iShouldSeeDashboardPage() {
        assertThat(page).hasTitle("Dashboard");
    }
}
