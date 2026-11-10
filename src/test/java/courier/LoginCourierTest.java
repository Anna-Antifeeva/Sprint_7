package courier;

import base.BaseCourierTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.Test;

import static data.APIData.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static steps.CourierSteps.loginCourier;
import static org.apache.http.HttpStatus.*;

public class LoginCourierTest extends BaseCourierTest {

@Test
@DisplayName("Login courier success")
@Description("The courier can log in, and their ID is returned in the response")

    public void testLoginCourierSuccess() {
    CourierModel courier = new CourierModel(LOGIN, PASSWORD);

    loginCourier(courier)
            .then()
            .log().all()
            .statusCode(SC_OK)
            .body("id", notNullValue());

}

    @Test
    @DisplayName("Login courier with empty required field login")
    @Description("If the login is not passed, error 400 is returned")

    public void testLoginCourierWithEmptyRequiredFieldLogin() {
        CourierModel courierWithoutLogin = new CourierModel(null, PASSWORD);

        loginCourier(courierWithoutLogin)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));

    }
        @Test
        @DisplayName("Login courier with empty required field password")
        @Description("If the password is not passed, error 400 is returned")

        public void testLoginCourierWithEmptyRequiredFieldPassword() {
        CourierModel courierWithoutPassword = new CourierModel(LOGIN, null);

        loginCourier(courierWithoutPassword)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Login courier with wrong field password")
    @Description("If the password is incorrect, error 404 is returned")

    public void testLoginCourierWithWrongFieldPassword() {
        CourierModel courierWrongPassword = new CourierModel(LOGIN, WRONG_PASSWORD);

        loginCourier(courierWrongPassword)
                .then()
                .log().all()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Login courier with wrong field login")
    @Description("If the login is incorrect, error 404 is returned")

        public void testLoginCourierWithWrongFieldLogin() {

        CourierModel courierWrongLogin = new CourierModel(WRONG_LOGIN, PASSWORD);

        loginCourier(courierWrongLogin)
                .then()
                .log().all()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));

    }


}
