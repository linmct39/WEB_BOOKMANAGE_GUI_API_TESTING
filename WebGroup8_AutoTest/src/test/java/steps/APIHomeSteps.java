package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APIHomeSteps {
    private Response apiResponse;

    @When("I execute the API to get book list")
    public void iExecuteTheAPIToGetBookList() {
        try {
            RestAssured.baseURI = "https://daw-bookstore-be.onrender.com";
            String strGetBooksUrl = "/books/"; 

            apiResponse = given()
                    .header("Accept", "application/json")
                    .when()
                    .get(strGetBooksUrl)
                    .then()
                    .extract()
                    .response();

            System.out.println("Response API Get Books: " + apiResponse.asString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The API should return status code {int} for books")
    public void theAPIShouldReturnStatusCodeForBooks(int expectedStatusCode) {
        try {
            assertEquals(expectedStatusCode, apiResponse.statusCode());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("The API should return a list containing books")
    public void theAPIShouldReturnAListContainingBooks() {
        try {
            String responseBody = apiResponse.asString();
            assertTrue("Dữ liệu không phải là danh sách array", responseBody.trim().startsWith("["));
            assertTrue("Danh sách sách trống", responseBody.contains("\"id\""));
            assertTrue("Danh sách sách trống", responseBody.contains("\"title\""));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
