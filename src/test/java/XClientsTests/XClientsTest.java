package XClientsTests;

import ConfigHelper.ConfigHelper;
import ConnectAPI.XClientsWebClient;
import EntityDB.CompanyEntity;
import EntityDB.EmployeeEntity;
import PersistensUnitInfo.PersUnInfo;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import model.Company;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.*;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XClientsTest {
    public static String TOKEN;
    public static int idCompany;
    static XClientsWebClient client = new XClientsWebClient();
    public EntityManager entityManager;

    @BeforeAll
    public static void authAndCreateCompany() {

        TOKEN = client.getToken(XClientsWebClient.login, XClientsWebClient.pass);
        idCompany = client.createCompany(XClientsWebClient.nameComp, XClientsWebClient.descriptComp, TOKEN);
    }

    @BeforeEach
    public void setUpDB() {

        Properties properties = new Properties();

        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url", ConfigHelper.getConnectionStringDB());
        properties.put("hibernate.connection.username", ConfigHelper.getUserNameDB());
        properties.put("hibernate.connection.password", ConfigHelper.getPasswordDB());
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        PersistenceUnitInfo persistenceUnitInfo = new PersUnInfo(properties);
        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory factory = hibernatePersistenceProvider.createContainerEntityManagerFactory(persistenceUnitInfo, persistenceUnitInfo.getProperties());
        entityManager = factory.createEntityManager();
    }

    @Test
    @Owner("Сергей Тарасов")
    @DisplayName("Task_1: Проверить, что список компаний фильтруется по параметру active")
    @Description("Запрашивем список активных компаний и впроверяем, что в ответе значение isActive везде true")
    @Tags({@Tag("company"), @Tag("positive")})
    public void companyFilteredByTrue() {

        given().log().all()
                .when().get(XClientsWebClient.URL_POST_COMPANY + "?active=true")
                .then()
                .statusCode(200)
                .body("isActive", everyItem(equalTo(true)))
                .log().all();
    }

    @Test
    @Owner("Сергей Тарасов")
    @DisplayName("Task_2: Проверить создание сотрудника в несуществующей компании")
    @Description("Создаем сотрудника с валидными значениями в несуществующей компании")
    @Tags({@Tag("employee"), @Tag("negative")})
    public void createEmployeeNonExistentCompany() {
        Response response = client.createEmployee(XClientsWebClient.firstName, XClientsWebClient.lastName,
                XClientsWebClient.middleName, 0, XClientsWebClient.email,
                "", XClientsWebClient.phone, true, TOKEN);

        response.then().statusCode(500);
    }

    @Test
    @Owner("Сергей Тарасов")
    @DisplayName("Task_3: Проверить, что неактивный сотрудник не отображается в списке")
    @Description("Создаем несколько сотрудников и меняем у одного параметр isActive c True на False, проверяем список")
    @Tags({@Tag("employee"), @Tag("positive")})
    public void checkNonActiveEmpNotDislpayed() {
        Response response = client.createEmployee(XClientsWebClient.firstName, XClientsWebClient.lastName,
                XClientsWebClient.middleName, idCompany, XClientsWebClient.email,
                "", XClientsWebClient.phone, true, TOKEN);

        int idNNewEmployee1 = response.then().statusCode(201).extract().path("id");

        EmployeeEntity newEmployee1 = entityManager.find(EmployeeEntity.class, idNNewEmployee1);
        assertEquals(idNNewEmployee1, newEmployee1.getId());

        given()
                .log().all()
                .body(XClientsWebClient.jsonChangeEmployeeIsActiveFalse)
                .header("x-client-token", TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .patch(XClientsWebClient.URL_GET_EMPLOYEEID + idNNewEmployee1)
                .then()
                .statusCode(200);

        Response response2 = client.createEmployee(XClientsWebClient.firstName, XClientsWebClient.lastName,
                XClientsWebClient.middleName, idCompany, XClientsWebClient.email,
                "", XClientsWebClient.phone, true, TOKEN);

        int idINewEmployee2 = response2.then().statusCode(201).extract().path("id");

        EmployeeEntity IsActiveNewEmployee = entityManager.find(EmployeeEntity.class, idINewEmployee2);
        assertEquals(idINewEmployee2, IsActiveNewEmployee.getId());


        given()
                .log().all()
                .when()
                .get(XClientsWebClient.URL_GET_EMPLOYEELIST + idCompany)
                .then()
                .statusCode(200)
                .body("isActive", everyItem(equalTo(true)))
                .log().all();
    }

    @Test
    @Owner("Сергей Тарасов")
    @DisplayName("Task_4: Проверить, что у удаленной компании проставляется в БД поле deletedAt")
    @Description("Удаляем компанию и проверяем в БД, что значение поля deletedAt не равно null")
    @Tags({@Tag("company"), @Tag("positive")})
    public void checkDBdeletedAtCompany() {

        given()
                .log().all()
                .header("x-client-token", TOKEN)
                .when()
                .get(XClientsWebClient.URL_DELETED_COMPANY + idCompany)
                .then()
                .statusCode(200);

        Company compInfo = client.getCompanyInfo(idCompany);
        int idDeletedCompany = compInfo.id();

        CompanyEntity companyEntity = entityManager.find(CompanyEntity.class, idDeletedCompany);
        assertNotNull(companyEntity.getDeletedAt());
    }
}
