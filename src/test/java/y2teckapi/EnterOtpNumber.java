package y2teckapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static y2teckapi.LogInApi.*;

public class EnterOtpNumber {
  @Test
  public void enterOtpNumber() {
//    LogInApi l = new LogInApi();
//    l.LoginToApplication();
//    GenerateOtp g = new GenerateOtp();
//    g.sendOtpMessageToUser();
    String endpoint =
        "http://user-ms.dev-bot.y2tek.io/user/supery2tek/verifyOtp?otp=123456&onEmail=true";

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
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.statusCode(200);
    int statusCode = response.getStatusCode();
    System.out.println("Status Code: " + statusCode);
    validatableResponse.body("message", Matchers.equalTo("Otp Verified Successfully."));
  }
}
