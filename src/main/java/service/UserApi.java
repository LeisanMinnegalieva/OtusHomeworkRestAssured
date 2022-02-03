package service;

import data.UserData;
import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class UserApi {
    public static final String BASE_URI = "https://petstore.swagger.io/v2/";
    public static RequestSpecification spec;
    public static final String PATH = "/user";


    public UserApi() {
        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response createUser(User user) {
        return given(spec)
                .body(user)
                .log().all()
                .when()
                .post(PATH);
    }

    public Response fetchUser() {
        return given(spec)
                .log().all()
                .when()
                .get(PATH + "/" + UserData.name);
    }

    public Response fetchUserSecond() {
        return given(spec)
                    .log().all()
                    .when()
                    .get(PATH+ "/" +"Antuan");

    }
}
