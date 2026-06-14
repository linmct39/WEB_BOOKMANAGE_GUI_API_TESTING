package pages;

import core.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    By inputQuantity = By.xpath("//input[@type='number']");
    By btnAddToCart = By.xpath("//button[contains(., 'Thêm Vào Giỏ Hàng')]");
    By cartBadge = By.xpath("//span[contains(@class, 'absolute') and contains(@class, '-top-1')]");

    BaseTest baseTest = new BaseTest();
    int intTimeOut = 10;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProduct(String productName) {
        try {
            By productLocator = By.xpath("//h3[text()='" + productName + "']");
            baseTest.waitUntilElementLocated(driver, productLocator, intTimeOut);
            driver.findElement(productLocator).click();
            baseTest.waitTime(2);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void increaseQuantityTo(int targetQty) {
        try {
            baseTest.waitUntilElementLocated(driver, inputQuantity, intTimeOut);
            driver.findElement(inputQuantity).clear();
            driver.findElement(inputQuantity).sendKeys(String.valueOf(targetQty));
            baseTest.waitTime(1);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void clickAddToCart() {
        try {
            driver.findElement(btnAddToCart).click();
            baseTest.waitTime(2);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void verifyCartBadge(String expectedCount) {
        try {
            baseTest.waitUntilElementLocated(driver, cartBadge, intTimeOut);
            String count = driver.findElement(cartBadge).getText();
            Assert.assertEquals(expectedCount, count);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void openCart() {
        try {
            driver.findElement(cartBadge).click();
            baseTest.waitTime(2);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void verifyCartItem(String expectedName, String expectedQty, String expectedTotal) {
        try {
            By nameLoc = By.xpath("//h3[contains(@class, 'text-gray-900') and contains(text(), '" + expectedName + "')]");
            baseTest.waitUntilElementLocated(driver, nameLoc, intTimeOut);
            Assert.assertTrue(driver.findElement(nameLoc).isDisplayed());

            By qtyLoc = By.xpath("//span[contains(@class, 'font-mono') and contains(text(), '" + expectedQty + "')]");
            baseTest.waitUntilElementLocated(driver, qtyLoc, intTimeOut);
            Assert.assertTrue(driver.findElement(qtyLoc).isDisplayed());

            String cleanTotal = expectedTotal.replace(" đ", "").trim();
            By totalLoc = By.xpath("//p[contains(@class, 'font-sans') and contains(text(), '" + cleanTotal + "')]");
            baseTest.waitUntilElementLocated(driver, totalLoc, intTimeOut);
            Assert.assertTrue(driver.findElement(totalLoc).isDisplayed());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
