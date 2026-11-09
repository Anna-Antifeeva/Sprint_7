package base;

import model.CourierModel;
import org.junit.After;
import org.junit.Before;

import static data.APIData.*;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.deleteCourier;

public class BaseCourierTest extends BaseAPITest {

    @Before

    public void createTestCourier() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then()
                .statusCode(201);

    }

    @After

    public void deleteTestCourier() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        deleteCourier(courier);

    }

}
