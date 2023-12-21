package main.java.base;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TabSwitcher extends BaseTest {

    private WebDriver driver;
    private String firstWindowHandle;

    public TabSwitcher(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToNewTabAndAssertURL(String expectedURLPattern) {
        try {
            firstWindowHandle = driver.getWindowHandle();
            waitUtility.waitForNewTab(driver);

            switchToNewTab();

            Thread.sleep(2000);

            assertURLMatchesPattern(expectedURLPattern);
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
        } finally {
            waitUtility.closeAndSwitchToFirstTab(driver, firstWindowHandle);
        }
    }

    private void switchToNewTab() {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(firstWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    private void assertURLMatchesPattern(String expectedURLPattern) {
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.matches(expectedURLPattern), "URL pattern doesn't match. " + currentURL);
    }
}
