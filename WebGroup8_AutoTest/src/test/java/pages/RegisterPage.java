package pages;

import core.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    WebDriver driver;
    By linkRegister = By.xpath("//a[@href='/signup']");
    By txtName = By.xpath("(//input[@type='text'])[1]");
    By txtUsername = By.xpath("(//input[@type='text'])[2]");
    By txtEmail = By.xpath("//input[@type='email']");
    By txtPassword = By.xpath("(//input[@type='password'])[1]");
    By txtConfirmPassword = By.xpath("(//input[@type='password'])[2]");
    By btnRegister = By.xpath("//button[@type='submit']");
    By emptyFieldErrorMessage = By.xpath("//span[contains(@class, 'error') or contains(text(), 'không được để trống')]");
    By emailExistsErrorMessage = By.xpath("//span[contains(text(), 'đã tồn tại')]");

    BaseTest baseTest = new BaseTest();
    int intTimeOut = 10;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterLink() {
        try {
            baseTest.waitUntilElementLocated(driver, linkRegister, intTimeOut);
            driver.findElement(linkRegister).click();
            baseTest.waitTime(1);
        } catch (Exception e) {
            Assert.fail("Failed to click register link: " + e.getMessage());
        }
    }

    public void funcRegister(String name, String username, String email, String password, String confirmPassword) {
        try {
            baseTest.waitUntilElementLocated(driver, txtName, intTimeOut);
            driver.findElement(txtName).sendKeys(name);
            driver.findElement(txtUsername).sendKeys(username);
            driver.findElement(txtEmail).sendKeys(email);
            driver.findElement(txtPassword).sendKeys(password);
            driver.findElement(txtConfirmPassword).sendKeys(confirmPassword);
            driver.findElement(btnRegister).click();
            baseTest.waitTime(2);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public boolean isEmptyFieldErrorMessageDisplayed() {
        try {
            baseTest.waitTime(1);
            return driver.findElements(emptyFieldErrorMessage).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailExistsErrorMessageDisplayed() {
        try {
            baseTest.waitTime(1);
            return driver.findElements(emailExistsErrorMessage).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
