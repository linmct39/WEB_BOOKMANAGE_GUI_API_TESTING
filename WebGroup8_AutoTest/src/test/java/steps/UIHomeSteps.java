package steps;

import core.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class UIHomeSteps extends BaseTest {
    WebDriver driver;
    HomePage homePage;

    @Before("@UIHome")
    public void setUp() {
        setupDriver();
        this.driver = getDriver();
        homePage = new HomePage(driver);
    }

    @After("@UIHome")
    public void tearDown() {
        removeDriver();
    }

    @And("I click on home link")
    public void iClickOnHomeLink() {
        if (homePage == null) {
            homePage = new HomePage(getDriver());
        }
        homePage.clickHomeLink();
    }

    @Then("The system should display the list of books")
    public void theSystemShouldDisplayTheListOfBooks() {
        Assert.assertTrue("Không hiển thị danh sách sách trên trang chủ!", homePage.isBookListDisplayed());
    }
}
