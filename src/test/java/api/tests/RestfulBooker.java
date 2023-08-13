package api.tests;
import com.shaft.driver.SHAFT;

import com.shaft.api.RestActions;
import api.restful.OM.RestfulBookerApi;
import api.restful.OM.ResrfulBookerApiBooking;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestfulBooker {

    private SHAFT.API apiObject;
    private RestfulBookerApi restfulBookerApi;
    private ResrfulBookerApiBooking resrfulBookerApiBooking;


@BeforeClass
public void beforeClass() {
        // Initialise the API Driver and the object classes objects to start from here!
        apiObject = new SHAFT.API(RestfulBookerApi.BASE_URL);
        restfulBookerApi = new RestfulBookerApi(apiObject);
        resrfulBookerApiBooking = new ResrfulBookerApiBooking(apiObject);

        restfulBookerApi.login("admin", "password123");
}
@Test
    public void getbookingIds(){resrfulBookerApiBooking.getBookingIds();}


@Test
    public void getbooking(){resrfulBookerApiBooking.getBooking("1");}

    @Test(description = "Create Booking")
    public void createBooking() {
        // Create Booking

        Response createBookingRes = resrfulBookerApiBooking.createBooking("Mahmoud", "ElSharkawy", 1000, true, "2020-01-01",
                "2021-01-01", "IceCream");
        // Get the created booking id
        String bookingId = RestActions.getResponseJSONValue(createBookingRes, "bookingid");
       resrfulBookerApiBooking.getBooking(bookingId);
        apiObject.assertThatResponse().extractedJsonValue("firstname").isEqualTo("Amira").perform();
        apiObject.assertThatResponse().extractedJsonValue("lastname").isEqualTo("ElSayed").perform();
        apiObject.assertThatResponse().extractedJsonValue("bookingdates.checkin").isEqualTo("2020-01-01").perform();
        apiObject.assertThatResponse().extractedJsonValue("bookingdates.checkout").isEqualTo("2021-01-01").perform();
        apiObject.assertThatResponse().extractedJsonValue("totalprice").isEqualTo("1000").perform();

        apiObject.assertThatResponse()
                .isEqualToFileContent(System.getProperty("testJsonFolderPath") + "RestfulBooker/booking.json")
                .perform();

    }

    /*
    @Test(description = "Delete Booking", dependsOnMethods = { "createBooking" })
    public void deleteBooking() {
       Response getBookingId = resrfulBookerApiBooking.getBookingIds("Amira", "ElSayed");

        String bookingId = RestActions.getResponseJSONValue(getBookingId, "$[0].bookingid");
        resrfulBookerApiBooking.deleteBooking(bookingId);
        apiObject.assertThatResponse().extractedJsonValue("$").isEqualTo("Created").perform();


    }*/




}
