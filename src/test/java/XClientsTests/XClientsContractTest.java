package XClientsTests;

import ConnectAPI.XClientsWebClient;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class XClientsContractTest {

    public static String TOKEN;
    @BeforeAll
    @DisplayName("Авторизация")
    @Description("Авторизуемся в качестве администратора")
    public static void AuthAndGetToken() {

        String jsonCreds =
                "{\"username\": \"" + XClientsWebClient.login + "\",\"password\": \"" + XClientsWebClient.pass + "\"}";

        TOKEN = given().log().all()
                .body(jsonCreds)
                .contentType(ContentType.JSON)
                .when().post(XClientsWebClient.URL_AUTH)
                .then().log().all()
                .statusCode(201)
                .extract().path("userToken");
    }

    @Test
    @Owner("Сергей Тарасов")
    @DisplayName("Создание тестовой компании")
    @Description("Создаем тестовую компанию, получаем ее id и проверяем ответы сервера")
    @Tags({@Tag("company")})
    public void canCreateCompany() {

        String jsonCompData =
                "{\"name\": \"" + XClientsWebClient.nameComp + "\",\"description\": \"" + XClientsWebClient.descriptComp + "\"}";

        int newCompanyId = given().log().all()
                .body(jsonCompData)
                .header("x-client-token", TOKEN)
                .contentType(ContentType.JSON)
                .when().post(XClientsWebClient.URL_POST_COMPANY)
                .then()
                .statusCode(201)
                .extract().path("id");

        given().log().all()
                .pathParam("id", newCompanyId)
                .get(XClientsWebClient.URL_ID_COMP)
                .then().log().all()
                .statusCode(200)
                .body("name", equalTo(XClientsWebClient.nameComp))
                .body("description", equalTo(XClientsWebClient.descriptComp))
                .body("deletedAt", equalTo(null))
                .body("isActive", equalTo(true));
    }

    @Test
    @Owner("Сергей Тарасов")
    @DisplayName("Получение списка компаний")
    @Description("Отправляем запрос на получение списка компаний, проверяем ответ сервера")
    @Tags({@Tag("company")})
    public void shouldBeReturnListOfCompanies() {

        given().log().all()
                .when().get(XClientsWebClient.URL_POST_COMPANY)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("vary", "Accept-Encoding")
                .body(is(notNullValue()));
    }
}
