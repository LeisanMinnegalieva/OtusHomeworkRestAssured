package createUser;

import dto.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserApi;

import static data.UserData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static service.UserApi.BASE_URI;
import static service.UserApi.PATH;


public class FetchUserTests {

        @Test
        @DisplayName("Получение созданного пользователя по имени")
        public void checkFetchCreatedUser() {
            UserApi userApi = new UserApi();

            Response response = userApi.fetchUser();
            User actualResponse= response.then()
                    .log().all()
                    .extract()
                    .body()
                    .as(User.class);

            Assertions.assertEquals(200, response.getStatusCode());

            User expectedResponse = new User(
                    email,
                    fistName,
                    180,
                    lastName,
                    password,
                    phone,
                    userStatus,
                    name
            );

            Assertions.assertEquals(expectedResponse, actualResponse);
        }

    @Test
    @DisplayName("Получение несуществующего пользователя по имени")
    public void checkFetchNonExistingUser() {
        UserApi userApi = new UserApi();

        userApi.fetchUserSecond()
                .then()
                .log().all()
                .statusCode(404)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/FetchUserError.json"))
                .body("type", equalTo("error"))
                .body("message", equalTo("User not found"))
                .body("code", equalTo(1));
    }

    @Test
    @DisplayName("Получение пользователя (обязательный параметр не передан)")
    public void checkFetchUserWrongRequest() {

        given()
                .baseUri(BASE_URI)
                .log().all()
                .when()
                .get(PATH)
                .then()
                .log().all()
                .statusCode(405)
                .contentType("application/xml");
    }
}
