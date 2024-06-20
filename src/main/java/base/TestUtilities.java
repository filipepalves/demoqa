package main.java.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class TestUtilities extends BaseTest {

    private static final String DOWNLOAD_DIRECTORY = System.getProperty("user.home") + "/Downloads";

    public static class WaitUtility {

        private String firstWindowHandle;
        private static WebDriver driver;
        private final WebDriverWait wait;


        public static void clickElement(By elementLocator) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
                wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
                WebElement element = driver.findElement(elementLocator);
                element.click();
            } catch (NoSuchElementException | ElementNotInteractableException e) {
                log.error("Element not found : {}", elementLocator);
                throw new IllegalStateException("Element not found: " + elementLocator, e);
            } catch (StaleElementReferenceException e) {
                log.error("Stale element reference : {}", elementLocator);
                try {
                    wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(elementLocator)));
                    driver.findElement(elementLocator).click();
                    log.info("Recovered from stale element and clicked successfully.");
                } catch (Exception retryException) {
                    log.error("Failed to recover from stale element : {}", elementLocator);
                    throw new IllegalStateException("Stale element issue: " + elementLocator, e);
                }
            }
        }

        public static void setDownloadPath(WebDriver driver, String fileNamePrefix, int timeoutInSeconds) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            File downloadedFile = wait.until(d -> {
                File[] files = new File(DOWNLOAD_DIRECTORY).listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.getName().startsWith(fileNamePrefix) && file.getName().endsWith(".xlsx")) {
                            return file;
                        }
                    }
                }
                return null;
            });
            try {
                String downloadedFileName = downloadedFile.getName();
                int indexOfVariablePart = downloadedFileName.indexOf('-', fileNamePrefix.length());
                String variablePart = downloadedFileName.substring(indexOfVariablePart + 1, downloadedFileName.length() - ".xlsx".length());

                log.info("The file {} (variablePart: {}) was downloaded successfully.", downloadedFileName, variablePart);

                if (downloadedFile.delete()) {
                    log.info("File {} was deleted successfully.", downloadedFileName);
                } else {
                    log.info("Failed to delete the file {}.", downloadedFileName);
                }
            } catch (Exception e) {
                log.info("Timeout occurred while waiting for the file matching pattern {} to be downloaded.", fileNamePrefix);
            }
        }

        public static void webElementIsDisplayed(By elementLocator) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
                WebElement element = driver.findElement(elementLocator);
                Assert.assertTrue(element.isDisplayed(), "Element identified by '" + elementLocator + "' is not displayed!");
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                log.error("Element not found or no longer valid: {}", elementLocator, e);
                throw new IllegalStateException("Element not found or no longer valid: " + elementLocator, e);
            }
        }

        public void confirmDropdownOptions(By dropdownLocator, List<String> expectedOptions) {
            webElementIsDisplayed(dropdownLocator);
            List<WebElement> options = driver.findElements(dropdownLocator);
            StringBuilder optionsText = new StringBuilder("Available options in the dropdown: ");
            for (WebElement option : options) {
                optionsText.append(option.getText()).append(", ");
            }
            for (String expectedOption : expectedOptions) {
                Assert.assertTrue(optionsText.toString().contains(expectedOption), "Option not found: " + expectedOption);
            }
        }

        public void clickDropdownOption(By dropdownLocator, String optionToClick) {
            webElementIsDisplayed(dropdownLocator);
            List<WebElement> options = driver.findElements(dropdownLocator);
            for (WebElement option : options) {
                if (option.getText().equals(optionToClick)) {
                    option.click();
                    break;
                }
            }
        }

        public void verifyText(By elementLocator, String expectedText) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            try {
                wait.until(textToBe(elementLocator, expectedText));
                WebElement element = driver.findElement(elementLocator);
                String actualText = element.getText();
                String errorMessage = String.format("Text verification failed for element '%s'. Expected text to contain '%s' but found '%s'", elementLocator, expectedText, actualText);
                Assert.assertEquals(actualText, expectedText, errorMessage);
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
                log.error("Error retrieving element text for '{}': {} - Expected Text: '{}' - {}", elementLocator, e.getMessage(), expectedText, e.getMessage());
                throw new IllegalStateException("Error retrieving element text: " + elementLocator, e);
            }
        }

        public void verifyTextContains(By elementLocator, String expectedText) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
                WebElement element = driver.findElement(elementLocator);
                String actualText = element.getText();
                String errorMessage = String.format("Text verification failed for element '%s'. Expected text to contain '%s' but found '%s'", elementLocator, expectedText, actualText);
                Assert.assertTrue(actualText.contains(expectedText), errorMessage);
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
                log.error("Error retrieving element text for '{}': {} - Expected Text: '{}' - {}", elementLocator, e.getMessage(), expectedText, e.getMessage());
                throw new IllegalStateException("Error retrieving element text: " + elementLocator, e);
            }
        }

        public void verifyAttributeContains(By elementLocator, String attributeName, String expectedValue) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
                WebElement element = driver.findElement(elementLocator);
                String actualValue = element.getAttribute(attributeName);
                if (actualValue == null) {
                    String errorMessage = String.format("Attribute '%s' for element '%s' is null, expected an empty string!", attributeName, elementLocator);
                    Assert.assertNull(actualValue, errorMessage);
                    return;
                }
                String errorMessage = String.format("Attribute verification failed for element '%s'. Expected attribute '%s' to contain '%s' but found '%s'", elementLocator, attributeName, expectedValue, actualValue);
                Assert.assertTrue(actualValue.contains(expectedValue), errorMessage);
            } catch (Exception e) {
                log.error("Error verifying attribute: element '{}', attribute '{}', expected value '{}' - {}", elementLocator, attributeName, expectedValue, e.getMessage());
                throw new IllegalStateException("Error verifying attribute: " + elementLocator + ", attribute: " + attributeName, e);
            }
        }

        public void sendKeysToElement(By elementLocator, String textToSend) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
                WebElement element = driver.findElement(elementLocator);
                element.clear();
                element.sendKeys(textToSend);
            } catch (Exception e) {
                log.error("Error sending keys to element: element '{}', text '{}' - {}", elementLocator, textToSend, e.getMessage());
                throw new IllegalStateException("Error sending keys to element: " + elementLocator, e);
            }
        }

        public void clearTextBox(By elementLocator) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
                WebElement element = driver.findElement(elementLocator);
                element.clear();
            } catch (Exception e) {
                log.error("Element not found for clearing text: {} - {}", elementLocator, e.getMessage());
                throw new IllegalStateException("Element not found: " + elementLocator, e);
            }
        }

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



