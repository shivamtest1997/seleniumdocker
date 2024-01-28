package com.test;

import com.google.common.util.concurrent.Uninterruptibles;
import listener.ListenerTest;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import util.Config;
import util.Constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
@Listeners({ListenerTest.class})
public abstract class AbstractTest {
    protected WebDriver driver;
    public static final Logger log= LoggerFactory.getLogger(AbstractTest.class);
    @BeforeSuite
    public void setupConfig() {
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext context) throws MalformedURLException {

        if (Boolean.parseBoolean(Config.getProperties(Constants.GRID_ENABLED))) {
            this.driver = getRemoteDriver();
        } else {
            this.driver = getLocalDriver();
        }
        context.setAttribute(Constants.DRIVER,this.driver);
    }
    private WebDriver getLocalDriver() {

        System.setProperty(
                "webdriver.chrome.driver",
                "C://ChromeDriver//chromedriver-win64//chromedriver-win64//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("no-sandbox");
        options.addArguments("test-type");
        //open in incognito
        options.addArguments("--incognito");
        //open in maximized window
        options.addArguments("--start-maximized");
        //disable notifications
        options.addArguments("--disable-notifications");
        options.setBinary("C://ChromeDriver//chrome-win64//chrome.exe");
        return driver = new ChromeDriver(options);
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        //http://localhost:4444/wd/hub
        Capabilities capabilities = new ChromeOptions();

        if (Constants.FIREFOX.equalsIgnoreCase(Config.getProperties(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        }
        //http://%s:4444/wd/hub  "http://localhost:4444/wd/hub"
        String urlFormat = Config.getProperties(Constants.GRID_URL_FORMAT);
        String hubHost = Config.getProperties(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("grid url: {}", url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    @AfterMethod
    public void delayToSeeSessions() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }
}
