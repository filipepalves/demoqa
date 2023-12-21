package main.java.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import main.java.base.TestUtilities.WaitUtility;

public class BaseTest {

    protected WebDriver driver;
    protected static WaitUtility waitUtility;
    protected JavascriptExecutor js;
    protected Actions actions;
    protected static Logger log;

    protected TabSwitcher tabSwitcher;



    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, ITestContext ctx) {

        String testName = this.getClass().getName();

        log = LogManager.getLogger(testName);

        Browser.BrowserDriverFactory factory = new Browser.BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        driver.manage().window().maximize();

        waitUtility = new WaitUtility(driver);
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        tabSwitcher = new TabSwitcher(driver);


    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        log.info("Browser closed");
    }
}
