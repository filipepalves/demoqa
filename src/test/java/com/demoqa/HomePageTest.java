package test.java.com.demoqa;

import main.java.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends TestUtilities {

    @Test
    public void homepagetest() {

        WaitUtility waitUtility = new WaitUtility(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        log.info("Starting Home page Test");

        String url = "https://demoqa.com/";
        driver.get(url);

        // Verify all the buttons

        By elementsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(1)");
        WebElement elements = driver.findElement(elementsclick);
        String elementsText = elements.getText();
        Assert.assertEquals(elementsText, "Elements");

        By formsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(2)");
        WebElement forms = driver.findElement(formsclick);
        String formsText = forms.getText();
        Assert.assertEquals(formsText, "Forms");

        By alertsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(3)");
        WebElement alerts = driver.findElement(alertsclick);
        String alertsText = alerts.getText();
        Assert.assertEquals(alertsText, "Alerts, Frame & Windows");

        By widgetsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(4)");
        WebElement widgets = driver.findElement(widgetsclick);
        String widgetsText = widgets.getText();
        Assert.assertEquals(widgetsText, "Widgets");

        By interactionsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(5)");
        WebElement interactions = driver.findElement(interactionsclick);
        String interactionsText = interactions.getText();
        Assert.assertEquals(interactionsText, "Interactions");

        By bookStoreclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(6)");
        WebElement bookStore = driver.findElement(bookStoreclick);
        String bookStoreText = bookStore.getText();
        Assert.assertEquals(bookStoreText, "Book Store Application");

        log.info("All the labels are verified.");

        // Click on each button

        waitUtility.waitAndClick(elementsclick);

        driver.navigate().back();

        waitUtility.waitAndClick(formsclick);

        driver.navigate().back();

        waitUtility.waitAndClick(alertsclick);

        driver.navigate().back();

        waitUtility.waitAndClick(widgetsclick);

        driver.navigate().back();

        waitUtility.waitAndClick(interactionsclick);

        driver.navigate().back();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        waitUtility.waitAndClick(bookStoreclick);

        log.info("All the buttons are working as expected.");

        log.info("Finishing Home page Test.");
    }
}

