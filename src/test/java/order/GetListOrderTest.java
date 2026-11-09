package order;

import base.BaseAPITest;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.getOrderList;

public class GetListOrderTest extends BaseAPITest {

    @Test

    public void getListOrderTest() {
        Response response = getOrderList();

        response.then()
                .log().all()
                .statusCode(200)
                .body("orders", notNullValue());

    }
}