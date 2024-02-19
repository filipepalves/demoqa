package main.java.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Browser {
    public static class BrowserDriverFactory {
        private final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
        private final String browser;
        private final Logger log;

        public BrowserDriverFactory(String browser, Logger log) {
            this.browser = browser.toLowerCase();
            this.log = log;
        }

        public WebDriver createDriver() {
            // Create driver
            log.info("Create driver: " + browser);

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;

                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    driver.set(new ChromeDriver(options));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;

                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver.set(new SafariDriver());
                    break;

                default:
                    System.out.println("Do not know how to start: " + browser + ", starting chrome.");
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;
            }

            return driver.get();
        }
    }
}