package pages;

import core.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    By linkLogin = By.xpath("//a[@href='/login']");
    By txtUsername = By.xpath("//input[@type='email']");
    By txtPassword = By.xpath("//input[@type='password']");
    By btnLogin = By.xpath("//button[@type='submit']");
    By loginErrorMessage = By.xpath("//span[text()='Email hoặc mật khẩu không chính xác']");
    BaseTest baseTest = new BaseTest();
    int intTimeOut = 10;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void funcLogin(String username, String password) {
        try {
            baseTest.waitUntilElementLocated(driver, linkLogin, intTimeOut);
            driver.findElement(linkLogin).click();
            baseTest.waitTime(1);
            
            baseTest.waitUntilElementLocated(driver, txtUsername, intTimeOut);
            driver.findElement(txtUsername).sendKeys(username);
            driver.findElement(txtPassword).sendKeys(password);
            driver.findElement(btnLogin).click();
            baseTest.waitTime(2);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public boolean isLoginErrorMessageDisplayed() {
        try {
            baseTest.waitTime(2);
            int errorCount = driver.findElements(loginErrorMessage).size();

            if (errorCount > 0) {
                System.out.println(" Hệ thống đã chặn đăng nhập và hiện cảnh báo.");
                return true;
            } else {
                System.out.println("Nhập sai thông tin nhưng không có cảnh báo nào hiện lên!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm thông báo lỗi đăng nhập: " + e.getMessage());
            return false;
        }
    }
}
