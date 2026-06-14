package steps;

import core.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

public class UICartSteps extends BaseTest {
    WebDriver driver;
    CartPage cartPage;

    @Before("@UICart")
    public void setUp() {
        setupDriver();
        this.driver = getDriver();
        cartPage = new CartPage(driver);
    }

    @After("@UICart")
    public void tearDown() {
        removeDriver();
    }

    @When("I click on product {string}")
    public void iClickOnProduct(String productName) {
        cartPage.clickProduct(productName);
    }

    @And("I increase quantity to {int} on detail page")
    public void iIncreaseQuantityToOnDetailPage(int qty) {
        cartPage.increaseQuantityTo(qty);
    }

    @And("I click add to cart button")
    public void iClickAddToCartButton() {
        cartPage.clickAddToCart();
    }

    @Then("The cart badge should display {string}")
    public void theCartBadgeShouldDisplay(String count) {
        cartPage.verifyCartBadge(count);
    }

    @When("I open the cart")
    public void iOpenTheCart() {
        cartPage.openCart();
    }

    @Then("The cart should contain product {string} with quantity {string} and total {string}")
    public void theCartShouldContainProductWithQuantityAndTotal(String name, String qty, String total) {
        cartPage.verifyCartItem(name, qty, total);
    }
}
