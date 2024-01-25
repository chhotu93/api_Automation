package y2teckapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import y2teckapi.readproperties.ReadPropertyFile;

import static org.hamcrest.Matchers.hasItem;

public class LogInApi {
  public static String aaccessToken;
  public static String idToken;
  public static String refreshToken;

  @Test
  public void LoginToApplication() {

    //    String jsonbody =
    //        "{\n"
    //            + "    \"email\": \"supery2tek@gmail.com\",\n"
    //            + "    \"password\": \"Y2tek@1234\",\n"
    //            + "    \"browser\": \"Chrome 120.0.0.0\",\n"
    //            + "    \"os\": \"Windows\",\n"
    //            + "    \"deviceType\": \"desktop\",\n"
    //            + "    \"ipAddress\": \"122.168.181.133\",\n"
    //            + "    \"timeZone\": \"Asia/Calcutta\",\n"
    //            + "    \"location\": \"\"\n"
    //            + "}";

    JSONObject js = new JSONObject();
    js.put("email", "supery2tek@gmail.com");
    js.put("password", "Y2tek@1234");
    js.put("browser", "Chrome 120.0.0.0");
    js.put("os", "Chrome 120.0.0.0");
    js.put("deviceType", "desktop");
    js.put("ipAddress", "122.168.181.133");
    js.put("timeZone", "Asia/Calcutta");
    js.put("location", "");

    RequestSpecification request = RestAssured.given();
    request.baseUri("http://user-ms.dev-bot.y2tek.io/login");
    request.contentType(ContentType.JSON);
    request.body(js.toString());
    Response response = request.post();

    //    // Printing Response as string
    //    System.out.println("test" + response.asString());

    // Extract user ID and password
    String CreateTwe = response.asString();
    JsonPath js1 = new JsonPath(CreateTwe);

    // Get Validatable response to perform validation
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.statusCode(200);
    //    System.out.println("validatableResponse: " + response.getStatusCode());
    //    System.out.println("id is" + js1.get("userName"));
    aaccessToken = js1.get("accessToken");
    idToken = js1.get("idToken");
    refreshToken = js1.get("refreshToken");
    validatableResponse.body("userName", Matchers.equalTo("supery2tek"));
    validatableResponse.body("userRole", hasItem("SuperAdmin"));
    validatableResponse.body("lastname", Matchers.equalTo(null));
  }
}
