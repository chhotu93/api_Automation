package y2teckapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static y2teckapi.LogInApi.*;

public class UsersPage {
  @Test
  public void userList() {
//    LogInApi l = new LogInApi();
//    l.LoginToApplication();
//    GenerateOtp g = new GenerateOtp();
//    g.sendOtpMessageToUser();
//    EnterOtpNumber enterOtpNumber = new EnterOtpNumber();
//    enterOtpNumber.enterOtpNumber();
    String endpoint = "http://user-ms.dev-bot.y2tek.io/user/totalUsers";

    // Perform GET request with Bearer and ID token
    Response response =
        RestAssured.given()
            .header("Authorization", "Bearer " + idToken)
            .header("X-REFRESH-TOKEN", refreshToken)
            .header("x-id-token", aaccessToken)
            .get(endpoint);

    // Extract and print the response body
    String responseBody = response.getBody().asString();
    System.out.println("Response Body: " + responseBody);
    int statusCode = response.getStatusCode();
    System.out.println("Status Code: " + statusCode);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.statusCode(200);

    validatableResponse.body("activeCount", Matchers.equalTo(825));
    validatableResponse.body("inActiveCount", Matchers.equalTo(19769));
    validatableResponse.body("totalCount", Matchers.equalTo(20594));
    validatableResponse.body("newCount", Matchers.equalTo(19727));
    validatableResponse.body("blockedCount", Matchers.equalTo(0));
  }
}
