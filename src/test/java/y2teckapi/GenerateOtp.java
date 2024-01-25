package y2teckapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static y2teckapi.LogInApi.*;

public class GenerateOtp {
  @Test
  public void sendOtpMessageToUser() {
    //        LogInApi l = new LogInApi();
    //    l.LoginToApplication();
    String endpoint = "http://user-ms.dev-bot.y2tek.io/user/supery2tek/generateOtp?onEmail=true";
    Response response =
        RestAssured.given()
            .header("Authorization", "Bearer " + idToken)
            .header("x-id-token", aaccessToken)
            .header("X-REFRESH-TOKEN", refreshToken)
            .get(endpoint);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.statusCode(200);
    validatableResponse.body("message", Matchers.equalTo("otp generated and sent successfully."));
  }
}
