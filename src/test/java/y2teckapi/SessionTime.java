package y2teckapi;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.io.IOException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SessionTime {
  public static ExtentHtmlReporter htmlReporter;
  public static ExtentReports extent;

  public static String Session() {

    RestAssured.baseURI = "http://user-ms.dev-bot.y2tek.io";
    Response Resp =
        given()
            .header("Content-Type", "application/json")
            .body(
                "{ \"email\": \"supery2tek@gmail.com\", \"password\": \"Y2tek@1234\",\"browser\": \"Chrome 120.0.0.0\", \"os\": \"Windows\",\"deviceType\": \"desktop\",\"ipAddress\": \"122.168.181.133\",\"timeZone\": \"Asia/Calcutta\",\"location\": \"\"} ")
            .when()
            .post("/login")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .response();

    String ResponseVariable = Resp.asString();
    //    System.out.println("Response as String Format is\t" + ResponseVariable);
    ValidatableResponse validatableResponse = Resp.then();
    validatableResponse.statusCode(200);
    long startTime = System.currentTimeMillis();
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    long RespTime = Resp.time();
    System.out.println("Response Time in Mili Second for Request is\t" + RespTime);

    JsonPath js = new JsonPath(ResponseVariable);
    String Sessionid1 = js.get("email");
    return Sessionid1;
  }

  @Test
  public void sessionTimeDuration() {
    Session();
  }
}
