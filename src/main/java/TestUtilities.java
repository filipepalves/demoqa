package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestUtilities extends BaseTest {

    public void waitForElementToBeClickable(By by) {
    }

    public void toString(String string) {
    }

    public static class WaitUtility {

        private final WebDriver driver;
        private final WebDriverWait wait;

        public WaitUtility(WebDriver driver) {
            this.driver = driver;
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        public void waitForVisibility(By locator) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        public void waitForElementToHaveText(By locator, String expectedText) {
            WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(org.openqa.selenium.NoSuchElementException.class);

            wait.until(d -> {
                WebElement element = d.findElement(locator);
                String actualText = element.getText();
                return actualText.equals(expectedText);
            });
        }

        public void waitForElementToHaveNumber(By locator) {
            WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(org.openqa.selenium.NoSuchElementException.class);

            wait.until(webDriver -> {
                WebElement element = webDriver.findElement(locator);
                try {
                    Integer.parseInt(element.getText().trim());
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            });
        }

        public void waitAndClick(By locator) {
            waitForVisibility(locator);
            driver.findElement(locator).click();
        }

        public String toString() {

            return null;
        }

        public void until() {

        }

        public void waitForNewTab(WebDriver driver) {
            int windowHandlesCount = driver.getWindowHandles().size();
            while (windowHandlesCount <= 1) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                windowHandlesCount = driver.getWindowHandles().size();
            }
        }

        public void waitFor(Duration duration) {
        }

        public void isElementPresent(By by) {
        }

        public void closeAndSwitchToFirstTab(WebDriver driver, String firstWindowHandle) {
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(firstWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    driver.close();
                    break;
                }
            }
            driver.switchTo().window(firstWindowHandle);
        }

        public void waitForElementToBeClickable(By waiftorfilipe) {

        }

        public void toString(String disable) {
        }
    }

}
