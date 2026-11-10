package order;

import base.BaseAPITest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.createOrder;
import static org.apache.http.HttpStatus.*;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest extends BaseAPITest {

    private final List<String> colours;

    public CreateOrderParameterizedTest(List<String> colours) {
        this.colours = colours;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {null}
        });
    }

    @Test
    @DisplayName("Create order with different colours")
    @Description("The order is successfully created with different color variations")

    public void testCreateOrderWithDifferentColours() {
        OrderModel order = new OrderModel(colours);

        createOrder(order)
                .then()
                .log().all()
                .statusCode(SC_CREATED)
                .body("track", notNullValue());
    }
}