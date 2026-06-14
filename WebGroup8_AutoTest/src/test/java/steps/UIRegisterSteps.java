package steps;

import core.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.RegisterPage;

public class UIRegisterSteps extends BaseTest {
    WebDriver driver;
    RegisterPage registerPage;

    @Before("@UIRegister")
    public void setUp() {
        setupDriver();
        this.driver = getDriver();
        registerPage = new RegisterPage(driver);
    }

    @After("@UIRegister")
    public void tearDown() {
        removeDriver();
    }

    @And("I click on register link")
    public void iClickOnRegisterLink() {
        registerPage.clickRegisterLink();
    }

    @When("I register with name {string}, username {string}, email {string}, password {string} and confirm password {string}")
    public void iRegisterWith(String name, String username, String email, String password, String confirmPassword) {
        registerPage.funcRegister(name, username, email, password, confirmPassword);
    }

    @Then("The system should display a required field validation message")
    public void theSystemShouldDisplayARequiredFieldValidationMessage() {
        Assert.assertTrue("Không hiển thị lỗi bắt buộc nhập!", registerPage.isEmptyFieldErrorMessageDisplayed());
    }

    @Then("The system should display an email exists error message")
    public void theSystemShouldDisplayAnEmailExistsErrorMessage() {
        Assert.assertTrue("Không hiển thị lỗi email đã tồn tại!", registerPage.isEmailExistsErrorMessageDisplayed());
    }
}
