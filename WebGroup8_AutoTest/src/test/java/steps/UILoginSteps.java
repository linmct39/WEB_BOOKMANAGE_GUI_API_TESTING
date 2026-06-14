package steps;

import core.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class UILoginSteps extends BaseTest {
    WebDriver driver;
    LoginPage loginPage;

    @Before("@UILogin")
    public void setUp() {
        setupDriver();
        this.driver = getDriver();
        loginPage = new LoginPage(driver);
    }

    @After("@UILogin")
    public void tearDown() {
        removeDriver();
    }

    @Given("I open web page WebGroup8")
    public void i_open_web_page_webgroup8() {
        try {
            System.out.println("Step: I open web page WebGroup8");
            getDriver().get("https://webgroup8.kietnetwork.com/");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        try {
            System.out.println("Step: I login with username: " + username + " | password: " + password);
            if (loginPage == null) {
                loginPage = new LoginPage(getDriver());
            }
            loginPage.funcLogin(username, password);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The system should display a login error message")
    public void the_system_should_display_a_login_error_message() {
        System.out.println("Step: Verify hiển thị cảnh báo đăng nhập thất bại");
        boolean isErrorShown = loginPage.isLoginErrorMessageDisplayed();
        Assert.assertTrue("Đăng nhập sai nhưng hệ thống không hiện thông báo lỗi!", isErrorShown);
    }

    @Then("I verify title page is {string}")
    public void i_verify_title_page_is(String expectedTitle) {
        System.out.println("Step: Verify title page is: " + expectedTitle);
        Assert.assertTrue("Tiêu đề không khớp", getDriver().getTitle().contains(expectedTitle));
    }
}
