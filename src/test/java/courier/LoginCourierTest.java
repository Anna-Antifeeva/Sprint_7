package courier;

import base.BaseCourierTest;
import model.CourierModel;
import org.junit.Test;

import static data.APIData.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static steps.CourierSteps.loginCourier;

public class LoginCourierTest extends BaseCourierTest {

@Test

    public void testLoginCourierSuccess() {
    CourierModel courier = new CourierModel(LOGIN, PASSWORD);

    loginCourier(courier)
            .then()
            .log().all()
            .statusCode(200)
            .body("id", notNullValue());

}

    @Test

    public void testLoginCourierWithEmptyRequiredFields() {
        CourierModel courierWithoutLogin = new CourierModel(null, PASSWORD);

        loginCourier(courierWithoutLogin)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));

        CourierModel courierWithoutPassword = new CourierModel(LOGIN, null);

        loginCourier(courierWithoutPassword)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test

    public void testLoginCourierWithWrongPassword() {
        CourierModel courierWrongPassword = new CourierModel(LOGIN, WRONG_PASSWORD);

        loginCourier(courierWrongPassword)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

        CourierModel courierWrongLogin = new CourierModel(WRONG_LOGIN, PASSWORD);

        loginCourier(courierWrongLogin)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

    }


}
