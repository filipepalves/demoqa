package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public class TableVerifier {
        public static void verifyColumnSorting(WebElement header, List<WebElement> tableRows, int columnIndex) throws InterruptedException {
            Thread.sleep(500);
            header.click();

            List<String> columnData = new ArrayList<>();
            for (WebElement row : tableRows) {
                List<WebElement> rowData = row.findElements(By.xpath(".//div[@role='gridcell' and contains(@class, 'rt-td')][" + columnIndex + "]"));
                if (rowData.size() > 0 && !rowData.get(0).getText().isEmpty()) {
                    columnData.add(rowData.get(0).getText().trim());
                }
            }
            columnData.removeIf(String::isEmpty);

            List<String> sortedColumnData;

            boolean isNumeric = columnData.stream().allMatch(s -> s.matches("\\d+"));

            if (isNumeric) {
                List<Integer> numericData = columnData.stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                List<Integer> sortedNumericData = new ArrayList<>(numericData);
                Collections.sort(sortedNumericData);
                sortedColumnData = sortedNumericData.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList());
            } else {
                sortedColumnData = new ArrayList<>(columnData);
                Collections.sort(sortedColumnData);
            }

            log.info("Sorted 'Column " + columnIndex + "' Data: " + sortedColumnData);

            boolean isColumnSorted = columnData.equals(sortedColumnData);
            Assert.assertTrue(isColumnSorted, "Column " + columnIndex + " is not sorted correctly.");
        }
    }


}
