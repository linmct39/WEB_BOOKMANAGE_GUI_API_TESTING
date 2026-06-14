package core;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    private static final ThreadLocal<WebDriver> threadWebDriver = new InheritableThreadLocal<WebDriver>();
    protected WebDriverWait wait;

    public BaseTest() {
        super();
    }

    public static WebDriver getDriver() {
        return threadWebDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        threadWebDriver.set(driver);
    }

    public static void removeDriver() {
        if (threadWebDriver.get() != null) {
            threadWebDriver.get().quit();
            threadWebDriver.remove();
        }
    }

    public void setupDriver() {
        if (getDriver() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            setDriver(driver);
        }
    }

    public void waitUntilElementLocated(WebDriver driver, By locator, int timeOutInSec) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Unable to locate web element: " + e.getMessage());
        }
    }

    public void waitTime(int intSecond) {
        try {
            Thread.sleep(intSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
