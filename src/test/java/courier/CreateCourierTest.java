package courier;

import base.BaseAPITest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.After;
import org.junit.Test;

import static data.APIData.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static steps.CourierSteps.*;

public class CreateCourierTest extends BaseAPITest {

@Test
@DisplayName("Create courier success")
@Description("When valid data is transmitted, the courier is created and the code 201 and ok: true are returned")

public void testCreateCourierSuccess() {
    CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

createCourier(courier)
        .then()
        .log().all()
        .statusCode(SC_CREATED)
        .body("ok", equalTo(true));

}

@Test
@DisplayName("Create courier error")
@Description("Cannot create two couriers with the same login; error 409 is returned")

public void testCreateCourierError() {
    CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

    createCourier(courier)
            .then()
            .log().all()
            .statusCode(SC_CREATED);

    createCourier(courier)
            .then()
            .log().all()
            .statusCode(SC_CONFLICT)
            .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

}

@Test
@DisplayName("Create courier with empty required field login")
@Description("If the login is not passed, error 400 is returned")

public void testCreateCourierWithEmptyRequiredFieldLogin() {
    CourierModel courierWithoutLogin = new CourierModel(null, PASSWORD, FIRSTNAME);

    createCourier(courierWithoutLogin)
            .then()
            .log().all()
            .statusCode(SC_BAD_REQUEST)
            .body("message", equalTo("Недостаточно данных для создания учетной записи"));

}

    @Test
    @DisplayName("Create courier with empty required field password")
    @Description("If the password is not passed, error 400 is returned")

    public void testCreateCourierWithEmptyRequiredFieldPassword() {

    CourierModel courierWithoutPassword = new CourierModel(LOGIN, null, FIRSTNAME);

    createCourier(courierWithoutPassword)
            .then()
            .log().all()
            .statusCode(SC_BAD_REQUEST)
            .body("message", equalTo("Недостаточно данных для создания учетной записи"));

}


@After

public void cleanUp() {
    CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
    deleteCourier(courier);
    }
}

