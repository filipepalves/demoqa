package main.java.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class TestUtilities extends BaseTest {


    public static class WaitUtility {

        private String firstWindowHandle;
        private WebDriver driver;
        private final WebDriverWait wait;



        public WaitUtility(WebDriver driver) {
            this.driver = driver;
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        public void TabSwitcher(WebDriver driver) {
            this.driver = driver;
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

        public void waitForElementToBeClickable(By locator) {
            WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(NoSuchElementException.class);

            // Use ExpectedConditions for a concise and reliable approach
            wait.until(ExpectedConditions.elementToBeClickable(locator));
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

    public static void checkboxIsDisabled(WebDriver driver, String checkboxId) {
        WebElement checkboxElement = driver.findElement(By.cssSelector("input#" + checkboxId + ".custom-control-input"));

        if (checkboxElement.getAttribute("disabled") != null) {
            log.info("Checkbox " + checkboxId + " is disabled. Cannot be selected as expected.");
        }
    }

    public static void tryToClickDisabledCheckbox(WebDriver driver, String checkboxId) {
        WebElement checkboxElement = driver.findElement(By.cssSelector("input#" + checkboxId + ".custom-control-input"));

        if (checkboxElement.getAttribute("disabled") != null) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", checkboxElement);
            if (!checkboxElement.isSelected()) {
                log.info("Attempted click on disabled checkbox " + checkboxId + " did not change its state as expected.");
            } else {
                throw new AssertionError("Error: Attempted click on disabled checkbox changed its state. Test failed.");
            }
        }

    }

    public static void toggleAndRestoreCheckbox(WebDriver driver, String checkboxId) {
        WebElement checkboxElement = driver.findElement(By.cssSelector("input#" + checkboxId + ".custom-control-input"));

        if (checkboxElement.isEnabled()) {
            boolean initialState = checkboxElement.isSelected();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", checkboxElement);

            log.info("Checkbox " + checkboxId + " was clicked.");

            if (initialState) {
                if (!checkboxElement.isSelected()) {
                    log.info("Checkbox " + checkboxId + " was selected and now is deselected.");
                }
            } else {
                if (checkboxElement.isSelected()) {
                    log.info("Checkbox " + checkboxId + " was deselected and now is selected.");
                }
            }

            js.executeScript("arguments[0].click();", checkboxElement);

            log.info("Checkbox " + checkboxId + " was clicked again to restore its state.");

            if (checkboxElement.isSelected() == initialState) {
                log.info("Checkbox " + checkboxId + " was restored to its initial state correctly.");
            }
        }

        }

        public static void toggleCheckbox(WebDriver driver, String checkboxId) {
            WebElement checkboxElement = driver.findElement(By.cssSelector("input#" + checkboxId + ".custom-control-input"));

            if (checkboxElement.isEnabled()) {
                boolean initialState = checkboxElement.isSelected();

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", checkboxElement);

                log.info("Checkbox " + checkboxId + " was clicked.");

                if (initialState) {
                    if (!checkboxElement.isSelected()) {
                        log.info("Checkbox " + checkboxId + " was selected and now is deselected.");
                    }
                } else {
                    if (checkboxElement.isSelected()) {
                        log.info("Checkbox " + checkboxId + " was deselected and now is selected.");
                    }
                }

            }
    }
    }



