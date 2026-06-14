package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class APIRegisterSteps {
    private Response apiResponse;

    @When("I execute the API to register with name {string}, username {string}, email {string} and password {string}")
    public void iExecuteTheAPIToRegister(String name, String username, String email, String password) {
        try {
            RestAssured.baseURI = "https://bookking000.vercel.app";
            String strRegisterUrl = "/api/users/register"; 

            JSONObject registerParams = new JSONObject();
            registerParams.put("full_name", name);
            registerParams.put("username", username);
            registerParams.put("email", email);
            registerParams.put("password", password);

            apiResponse = given()
                    .header("Content-Type", "application/json")
                    .body(registerParams.toString())
                    .when()
                    .post(strRegisterUrl)
                    .then()
                    .extract()
                    .response();

            System.out.println("Response API Register: " + apiResponse.asString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The API should return status code {int} for register")
    public void theAPIShouldReturnStatusCodeForRegister(int expectedStatusCode) {
        try {
            assertEquals(expectedStatusCode, apiResponse.statusCode());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The API should reject the request with status code {int} for empty fields")
    public void theAPIShouldRejectForEmptyFields(int expectedStatusCode) {
        try {
            assertEquals(expectedStatusCode, apiResponse.statusCode());
            String responseBody = apiResponse.asString();
            boolean hasError = responseBody.contains("Thiếu thông tin đăng ký");
            Assert.assertTrue("Không báo lỗi trường bắt buộc", hasError);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The API should reject the request with status code {int} for existing email")
    public void theAPIShouldRejectForExistingEmail(int expectedStatusCode) {
        try {
            assertEquals(expectedStatusCode, apiResponse.statusCode());
            String responseBody = apiResponse.asString();
            boolean hasError = responseBody.contains("Username hoặc Email đã tồn tại");
            Assert.assertTrue("Không báo lỗi email đã tồn tại", hasError);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
