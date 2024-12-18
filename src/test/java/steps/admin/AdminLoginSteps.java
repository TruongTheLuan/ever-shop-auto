package steps.admin;

import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.platform.commons.util.StringUtils;

import static common.CommonUtils.ADMIN_LOGIN_PATH;
import static common.CommonUtils.COMMON_URL;
import static org.junit.jupiter.api.Assertions.assertNull;
import static steps.Hooks.page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminLoginSteps {
    @Given("I open admin login page")
    public void openAdminLoginPage(){
        page.navigate(String.format("%s%s",COMMON_URL,ADMIN_LOGIN_PATH));
        String buttonSignInXpath = "//button[normalize-space(.//text())='SIGN IN']";
        Locator buttonSignInLocator = page.locator(buttonSignInXpath);
        assertThat(buttonSignInLocator).isVisible();
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
