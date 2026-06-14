package pages;

import core.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {
    WebDriver driver;
    By linkHome = By.xpath("//a[@href='/']");
    By bookGrid = By.xpath("//div[contains(@class, 'grid')]");
    By bookTitles = By.xpath("//div[contains(@class, 'grid')]//h3");

    BaseTest baseTest = new BaseTest();
    int intTimeOut = 10;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickHomeLink() {
        try {
            baseTest.waitUntilElementLocated(driver, linkHome, intTimeOut);
            driver.findElement(linkHome).click();
            baseTest.waitTime(2);
        } catch (Exception e) {
            Assert.fail("Failed to click home link: " + e.getMessage());
        }
    }

    public boolean isBookListDisplayed() {
        try {
            baseTest.waitUntilElementLocated(driver, bookGrid, intTimeOut);
            List<WebElement> books = driver.findElements(bookTitles);
            return books.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
