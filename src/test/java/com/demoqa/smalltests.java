package test.java.com.demoqa;

import main.java.base.TestUtilities;
import main.java.pages.NewWindowPage;
import main.java.pages.WelcomePageObject;
import main.java.pages.WindowsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class smalltests extends TestUtilities {

    @Test
    public void elementstest() throws InterruptedException {

        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);

        log.info("Starting Elements Test");

        String url = "https://demoqa.com/";
        driver.get(url);

        // Click on elements button and verify all the elements
        By elementsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(1)");
        waitUtility.waitAndClick(elementsclick);

        js.executeScript("window.scrollBy(0, 300)");
        By linksclick = By.id("item-5");
        waitUtility.waitAndClick(linksclick);

        WebElement simplelink = driver.findElement(By.id("simpleLink"));
        String simplelinkText = simplelink.getText();
        Assert.assertEquals(simplelinkText, "Home");

        WebElement dynamiclink = driver.findElement(By.id("dynamicLink"));
        String dynamiclinkText = dynamiclink.getText();
        Assert.assertTrue(dynamiclinkText.contains("Home"));

        WebElement createdlink = driver.findElement(By.id("created"));
        String createdlinkText = createdlink.getText();
        Assert.assertEquals(createdlinkText, "Created");

        WebElement nocontent = driver.findElement(By.id("no-content"));
        String nocontentText = nocontent.getText();
        Assert.assertEquals(nocontentText, "No Content");

        WebElement moved = driver.findElement(By.id("moved"));
        String movedText = moved.getText();
        Assert.assertEquals(movedText, "Moved");

        WebElement badrequest = driver.findElement(By.id("bad-request"));
        String badrequestText = badrequest.getText();
        Assert.assertEquals(badrequestText, "Bad Request");


        WebElement unauthorized = driver.findElement(By.id("unauthorized"));
        String unauthorizedText = unauthorized.getText();
        Assert.assertEquals(unauthorizedText, "Unauthorized");

        WebElement forbidden = driver.findElement(By.id("forbidden"));
        String forbiddenText = forbidden.getText();
        Assert.assertEquals(forbiddenText, "Forbidden");

        WebElement notfound = driver.findElement(By.id("invalid-url"));
        String notfoundText = notfound.getText();
        Assert.assertEquals(notfoundText, "Not Found");


        // Click on MultipleWindows link
        WindowsPage windowsPage = welcomePage.clickMultipleWindowsLink();

        // Find and switch to new window page
        NewWindowPage newWindowPage = windowsPage.switchToNewWindowPage();

        String pageSource = newWindowPage.getCurrentPageSource();

        // Verification that new page contains expected text in source
        Assert.assertTrue(pageSource.contains("https://demoqa.com/"), "New page source doesn't contain expected text");

    }
}




