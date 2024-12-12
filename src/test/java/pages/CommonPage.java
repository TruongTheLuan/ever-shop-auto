package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class CommonPage {
    public Page page;
    public CommonPage(Page page){
        this.page=page;
    }

    public void selectMenuItem(String label) {
        String menuItemXpath = String.format("//a[./text()[normalize-space()='%s']]", label);
        Locator menuItemLocator = page.locator(menuItemXpath);
        menuItemLocator.click();
    }

    public void inputText(String label, String value) {
        String inputFieldXpath = String.format("//div[./label[normalize-space()='%s']]//input", label);
        Locator inputFieldLocator = page.locator(inputFieldXpath);
        inputFieldLocator.fill(value);
    }

    public void inputTextArea(String label, String value) {
        String inputAreaFieldXpath = String.format("//div[./label[normalize-space()='%s']]//textarea", label);
        Locator inputAreaFieldLocator = page.locator(inputAreaFieldXpath);
        inputAreaFieldLocator.fill(value);
    }

    public void selectRadioOption(String option, String radioLabel) {
        String radioXpath = String.format("//div[./label[normalize-space()='%s']]//span[text()[normalize-space()='%s']]//preceding::span[1]", radioLabel, option);
        Locator radioLocator = page.locator(radioXpath);
        radioLocator.click();
    }

    public void clickButtonByLabel(String label) {
        String loginButtonXpath = String.format("//button[normalize-space(.//text())='%s']", label);
        Locator loginButtonLocator = page.locator(loginButtonXpath);
        loginButtonLocator.click();
    }

    public void verifyPageWithHeader(String header) {
        String headerXpath = String.format("//h1[contains(concat(' ',normalize-space(@class),' '),' page-heading-title ') and ./text()[normalize-space()='%s']]", header);
        Locator headerLocator = page.locator(headerXpath);
        assertThat(headerLocator).isVisible();
    }
}
