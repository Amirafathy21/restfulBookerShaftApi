package api.restful.OM;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class RestfulBookerApi {

    private SHAFT.API apiObject;
    private RestActions api0bject;

    // Base URL
    public static final String BASE_URL =  "https://restful-booker.herokuapp.com/";
    // Services Names
    private String auth_serviceName = "auth";
    // Status Codes
    public static final int SUCCESS = 200;
    public static final int SUCCESS_DELETE = 201;




    // Constructor
    public RestfulBookerApi(SHAFT.API apiObject) {
            this.apiObject = apiObject;
        }

    @SuppressWarnings("unchecked")
    @Step("Login with Username: {username} and Password: {password}")
    public void login(String username, String password) {
        JSONObject authentication = new JSONObject();
        authentication.put("username", username);
        authentication.put("password", password);

        Response createToken = apiObject.post(auth_serviceName)
                .setRequestBody(authentication)
                .setContentType(ContentType.JSON).perform();

        String token = RestActions.getResponseJSONValue(createToken, "token");
        apiObject.addHeader("Cookie", "token=" + token);
    }







    }

