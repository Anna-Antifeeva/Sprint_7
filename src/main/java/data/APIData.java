package data;

import com.github.javafaker.Faker;

public class APIData {
   static Faker user = new Faker();

    public static final String LOGIN = user.name().lastName() + System.currentTimeMillis();
    public static final String PASSWORD = user.regexify("[0-9]{4}");
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String COURIER_PATH = "/api/v1/courier";
    public static final String COURIER_LOGIN = "/api/v1/courier/login";
    public static final String ORDER_PATH = "/api/v1/orders";
    public static final String WRONG_LOGIN = "fdkvkdnj";
    public static final String WRONG_PASSWORD = "sfefkmkdv";

}
