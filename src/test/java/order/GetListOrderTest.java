package order;

import base.BaseAPITest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.getOrderList;
import static org.apache.http.HttpStatus.*;

public class GetListOrderTest extends BaseAPITest {

    @Test
    @DisplayName("List order test")
    @Description("The endpointmvn allure:report returns code 200, and the response body contains a list of orders")

    public void getListOrderTest() {
        Response response = getOrderList();

        response.then()
                .log().all()
                .statusCode(SC_OK)
                .body("orders", notNullValue());

    }
}