package steps.admin;

import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertNull;
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

    @Then("I should see error message for {string} is {string}")
    public void verifyValidationMessage(String label, String errorMessage) {
        String validationMessageXpath = String.format("//div[./label[normalize-space(.//text())='%s']]//div[contains(concat(' ',normalize-space(@class),' '),' field-error ')]", label);
        Locator validationMessageLocator = page.locator(validationMessageXpath);
        if("".equals(errorMessage)){
            try{
                validationMessageLocator.waitFor(new Locator.WaitForOptions().setTimeout(1000));
            }catch (Exception exception){
                validationMessageLocator = null;
            }
            assertNull(validationMessageLocator);
        }else{
            assertThat(validationMessageLocator).hasText(errorMessage);
        }
    }
}
