package steps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import model.CourierModel;
import static org.apache.http.HttpStatus.*;

import static data.APIData.*;
import static io.restassured.RestAssured.given;

public class CourierSteps {

    @Step("Создание курьера")
    public static Response createCourier(CourierModel courier) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .extract().response();
    }

    @Step("Авторизация курьера")
    public static Response loginCourier(CourierModel courier) {

        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_LOGIN)
                .then()
                .extract().response();

    }

    @Step("Удаление курьера")
    public static void deleteCourier(CourierModel courier) {

        Response response = loginCourier(courier);

        if (response.statusCode() == SC_OK) {
            int id = response.path("id");
            given()
                    .delete(COURIER_PATH + "/" + id)
                    .then()
                    .statusCode(SC_OK);
        }
    }
}


