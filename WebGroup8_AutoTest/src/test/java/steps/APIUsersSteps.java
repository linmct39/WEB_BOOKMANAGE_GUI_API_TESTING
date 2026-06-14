package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APIUsersSteps {
    private Response apiResponse;
    @When("I execute the API to get users list")
    public void IExecuteTheAPIToGetUsersList (){
        try{
            RestAssured.baseURI="https://bookking000.vercel.app/";
            String strGetUsersUrl="/api/users";

            apiResponse=given()
                    .header("Accept","application/json")
                    .when()
                    .get(strGetUsersUrl)
                    .then()
                    .extract()
                    .response();
            System.out.println("Respone API Get Users: "+ apiResponse.asString());
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }


    @Then("The API should return status code {int} for users")
    public void theAPIShouldReturnStatusCodeForUsers(int expectedStatus){
        try{
            assertEquals(expectedStatus, apiResponse.statusCode());
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @And("The API should return a list containing users")
    public void theAPIShouldReturnAListContainingUsers(){
        try{
            String responeBody = apiResponse.asString();
            assertTrue("Dữ liệu không phải là danh sách Array", responeBody.trim().startsWith("["));
            assertTrue("Danh sách không có user nào (thiếu id)", responeBody.contains("\"id\""));
            assertTrue("Danh sách không có dữ liệu user (thiếu username)", responeBody.contains("\"username\""));
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
}
