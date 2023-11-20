package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    protected Logger log;

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, ITestContext ctx) {

        String testName = this.getClass().getName();

        log = LogManager.getLogger(testName);

        Browser.BrowserDriverFactory factory = new Browser.BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        driver.manage().window().maximize();

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        log.info("Browser closed");
    }
}
