package test.java.com.demoqa;

import main.java.base.TabSwitcher;
import main.java.base.TestUtilities;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class smalltests extends TestUtilities {

    @Test
    public void elementstest() throws InterruptedException {

        log.info("Starting Elements Test");

        String url = "https://demoqa.com/";
        driver.get(url);

        // Click on elements button and verify all the elements

        By elementsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(1)");
        waitUtility.waitAndClick(elementsclick);

        js.executeScript("window.scrollBy(0, 300)");
        tabSwitcher = new TabSwitcher(driver);




    }
}




