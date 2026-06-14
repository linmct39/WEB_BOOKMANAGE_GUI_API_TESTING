package pages;

import core.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage {
    WebDriver driver;
    By linkNavSearch = By.xpath("//a[@href='/search']");
    By txtSearch = By.xpath("//input[@placeholder='Nhập tựa sách, tác giả hoặc từ khóa liên quan...']");
    By btnSearch = By.xpath("//button[@type='submit' and contains(text(), 'Tìm kiếm')]");
    By bookTitles = By.xpath("//div[contains(@class, 'grid')]//h3");

    BaseTest baseTest = new BaseTest();
    int intTimeOut = 10;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNavSearchLink() {
        try {
            baseTest.waitUntilElementLocated(driver, linkNavSearch, intTimeOut);
            driver.findElement(linkNavSearch).click();
            baseTest.waitTime(1);
        } catch (Exception e) {
            Assert.fail("Failed to click nav search link: " + e.getMessage());
        }
    }

    public void enterSearchKeyword(String keyword) {
        try {
            baseTest.waitUntilElementLocated(driver, txtSearch, intTimeOut);
            WebElement searchInput = driver.findElement(txtSearch);
            searchInput.clear();
            searchInput.sendKeys(keyword);
            baseTest.waitTime(1);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void clickSearchButton() {
        try {
            driver.findElement(btnSearch).click();
            baseTest.waitTime(2); 
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public boolean isBookTitleDisplayed(String expectedTitle) {
        try {
            baseTest.waitUntilElementLocated(driver, bookTitles, intTimeOut);
            List<WebElement> titles = driver.findElements(bookTitles);
            for (WebElement titleElement : titles) {
                if (titleElement.getText().trim().equalsIgnoreCase(expectedTitle.trim())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
