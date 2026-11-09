package courier;

import base.BaseAPITest;
import model.CourierModel;
import org.junit.After;
import org.junit.Test;

import static data.APIData.*;
import static org.hamcrest.Matchers.equalTo;
import static steps.CourierSteps.*;

public class CreateCourierTest extends BaseAPITest {

@Test

public void testCreateCourierSuccess() {
    CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

createCourier(courier)
        .then()
        .log().all()
        .statusCode(201)
        .body("ok", equalTo(true));

}

@Test

public void testCreateCourierError() {
    CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

    createCourier(courier)
            .then()
            .log().all()
            .statusCode(201);

    createCourier(courier)
            .then()
            .log().all()
            .statusCode(409)
            .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

}

@Test

public void testCreateCourierWithEmptyRequiredFields() {
    CourierModel courierWithoutLogin = new CourierModel(null, PASSWORD, FIRSTNAME);

    createCourier(courierWithoutLogin)
            .then()
            .log().all()
            .statusCode(400)
            .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    CourierModel courierWithoutPassword = new CourierModel(LOGIN, null, FIRSTNAME);

    createCourier(courierWithoutPassword)
            .then()
            .log().all()
            .statusCode(400)
            .body("message", equalTo("Недостаточно данных для создания учетной записи"));

}


@After

public void cleanUp() {
    CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
    deleteCourier(courier);
    }
}

