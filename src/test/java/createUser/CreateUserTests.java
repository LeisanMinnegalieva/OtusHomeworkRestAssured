package createUser;

import dto.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserApi;

import static data.UserData.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTests {


    @Test
    @DisplayName("Создание пользователя при отправке всех атрибутов  сообщения")
    public void checkCreateUserWithAllAtributes() {

        User user = User.builder()
                .username(name)
                .phone(phone)
                .email(email)
                .userStatus(userStatus)
                .id(163)
                .firstName(fistName)
                .password(password)
                .lastName(lastName)
                .build();

        UserApi userApi = new UserApi();

        userApi.createUser(user)
                .then()
                .log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));
    }

        @Test
        @DisplayName("Создание пользователя при отправке 3х атрибутов сообщения.")
        public void checkCreateUserWithSeveralAtributes () {
            String expectedType = "unknown";

            User user = User.builder()
                    .username("TARA")
                    .id(160)
                    .password("32455")
                    .build();

            UserApi userApi = new UserApi();

            userApi.createUser(user)
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("type", equalTo(expectedType))
                    .body("message", equalTo(user.getId().toString()))
                    .body("code", equalTo(200));
    }
}
