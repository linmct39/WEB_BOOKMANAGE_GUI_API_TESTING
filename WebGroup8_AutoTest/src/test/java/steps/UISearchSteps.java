package steps;

import core.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SearchPage;

public class UISearchSteps extends BaseTest {
    WebDriver driver;
    SearchPage searchPage;

    @Before("@UISearch")
    public void setUp() {
        setupDriver();
        this.driver = getDriver();
        searchPage = new SearchPage(driver);
    }

    @After("@UISearch")
    public void tearDown() {
        removeDriver();
    }

    @And("I click on search navigation link")
    public void iClickOnSearchNavigationLink() {
        searchPage.clickNavSearchLink();
    }

    @When("I enter keyword {string} into search box")
    public void iEnterKeywordIntoSearchBox(String keyword) {
        searchPage.enterSearchKeyword(keyword);
    }

    @And("I click on the search button")
    public void iClickOnTheSearchButton() {
        searchPage.clickSearchButton();
    }

    @Then("The system should display book containing title {string}")
    public void theSystemShouldDisplayBookContainingTitle(String expectedTitle) {
        Assert.assertTrue("Không tìm thấy sách có tên: " + expectedTitle, searchPage.isBookTitleDisplayed(expectedTitle));
    }
}
