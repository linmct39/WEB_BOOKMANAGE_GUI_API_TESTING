package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class APILoginSteps {
    private Response apiResponse;

    @When("I execute the API to login with email {string} and password {string}")
    public void iExecuteTheAPIToLogin(String email, String password) {
        try {
            RestAssured.baseURI = "https://bookking000.vercel.app";
            String strLoginUrl = "/api/users/login";

            JSONObject loginParams = new JSONObject();
            loginParams.put("email", email);
            loginParams.put("password", password);

            apiResponse = given()
                    .header("Content-Type", "application/json")
                    .body(loginParams.toString())
                    .when()
                    .post(strLoginUrl)
                    .then()
                    .extract()
                    .response();

            System.out.println("Response API: " + apiResponse.asString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The API should return status code {int}")
    public void theAPIShouldReturnStatusCode(int expectedStatusCode) {
        try {
            assertEquals(expectedStatusCode, apiResponse.statusCode());
            System.out.println("Login Success: " + apiResponse.getBody().asString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The API should reject the request with status code {int}")
    public void theAPIShouldRejectTheRequest(int expectedStatusCode) {
        try {
            assertEquals(expectedStatusCode, apiResponse.statusCode());
            String responseBody = apiResponse.asString();
            boolean isLogicError = responseBody.contains("Email hoặc mật khẩu không chính xác") || responseBody.contains("Tài khoản chưa được kích hoạt");
            Assert.assertTrue("Không tìm thấy thông báo lỗi trong response", isLogicError);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
