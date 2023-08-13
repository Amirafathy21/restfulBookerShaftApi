package api.restful.OM;

import com.shaft.api.RestActions;
import org.json.simple.JSONObject;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ResrfulBookerApiBooking {
    private SHAFT.API apiObject;
    private RestActions api0bject;


    // Services Names
    private String booking_serviceName = "booking/";

    // Constructor


    public  ResrfulBookerApiBooking(SHAFT.API apiObject ){
        this.apiObject = apiObject;

    }


    @Step("Get all Booking Ids")
    public Response getBookingIds() {
        return apiObject.get(booking_serviceName).perform();}

    @Step("Get a Booking details by the Booking Id: [{bookingId}]")
    public Response getBooking(String bookingId) {
        return apiObject.get(booking_serviceName + bookingId).perform();}

    @Step("Create Booking")
    public Response createBooking(String firstName, String lastName, int totalPrice, boolean depositePaid,
                                  String checkIn, String checkOut, String additionalNeeds) {
        return apiObject.post(booking_serviceName)
                .setRequestBody(createBookingBody(firstName, lastName, totalPrice, depositePaid, checkIn, checkOut, additionalNeeds))
                .setContentType(ContentType.JSON)
		.perform();
    }

    private JSONObject createBookingBody(String firstName, String lastName, int totalPrice, boolean depositePaid,
                                         String checkIn, String checkOut, String additionalNeeds) {
        JSONObject createBookingBody = new JSONObject();
        createBookingBody.put("firstname", firstName);
        createBookingBody.put("lastname", lastName);
        createBookingBody.put("totalprice", totalPrice);
        createBookingBody.put("depositpaid", depositePaid);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkIn);
        bookingDates.put("checkout", checkOut);
        createBookingBody.put("bookingdates", bookingDates);
        createBookingBody.put("additionalneeds", additionalNeeds);

        return createBookingBody;}


    @Step("Delete a Booking by Id: [{bookingId}]")
    public Response deleteBooking(String bookingId) {
        return apiObject.delete(booking_serviceName + bookingId)
                .setTargetStatusCode(RestfulBookerApi.SUCCESS_DELETE).perform();
    }


}
