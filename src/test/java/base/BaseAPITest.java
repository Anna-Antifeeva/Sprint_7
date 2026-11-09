package base;

import io.restassured.RestAssured;
import org.junit.Before;

public class BaseAPITest {
    @Before

    public void setup() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }
}
