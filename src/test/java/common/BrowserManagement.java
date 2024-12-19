package common;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import io.cucumber.junit.platform.engine.Constants;

import static steps.Hooks.playwright;

public class BrowserManagement {
    public static Browser getBrowserInstance(){
        String browserType = ConfigUtils.getDotenv().get("browser");
        return switch(browserType){
            case "chrome" -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            default -> throw new IllegalStateException("Unexpected value: " + browserType);
        };
    }
}
