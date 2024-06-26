package ConnectAPI;

import ConfigHelper.ConfigHelper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Company;
import model.CreateCompany;
import model.CreateEmployee;

import static io.restassured.RestAssured.given;

public class XClientsWebClient {

    public static final String login = ConfigHelper.getLoginAdmin();
    public static final String pass = ConfigHelper.getPasswodAdmin();
    public static final String nameComp = ConfigHelper.getTestNameCompany();
    public static final String descriptComp = ConfigHelper.getTestDescriptCompany();
    public static final String firstName = ConfigHelper.getTestFirstNameEmpl();
    public static final String middleName = ConfigHelper.getTestMiddleNameEmpl();
    public static final String lastName = ConfigHelper.getTestLastNameEmpl();
    public static final String email = ConfigHelper.getTestEmailEmpl();
    public static final String phone = ConfigHelper.getTestPhoneEmpl();
    public static final String URL_ID_COMP = ConfigHelper.getUrlCompanyById();
    public static final String URL_AUTH = ConfigHelper.getUrlAuth();
    public static final String URL_POST_COMPANY = ConfigHelper.getUrlAddCompany();
    public static final String URL_DELETED_COMPANY = ConfigHelper.getUrlDeletedCompany();
    public static final String URL_GET_EMPLOYEELIST = ConfigHelper.getUrlEmployeeList();
    public static final String URL_GET_EMPLOYEEID = ConfigHelper.getUrlEmployeeById();
    public static final String jsonChangeEmployeeIsActiveFalse =
            "{\"lastName\": \"" + lastName + "\", \n" +
                    " \"email\": \"" + email + "\",\n" +
                    "  \"url\": \"\",\n" +
                    " \"phone\": \"" + phone + "\",\n" +
                    " \"isActive\": false}";

    @Step("Авторизуемся и получаем токен Админа")
    public String getToken(String login, String pass) {
        String jsonCreds = "{\"username\": \"" + login + "\",\"password\": \"" + pass + "\"}";

        return given().log().all()
                .body(jsonCreds)
                .contentType(ContentType.JSON)
                .when().post(URL_AUTH)
                .then().log().all()
                .extract().path("userToken");
    }

    @Step("Создаем тестовую компанию")
    public int createCompany(String name, String description, String token) {

        CreateCompany createCompany = new CreateCompany(name, description);
        return given().log().all()
                .body(createCompany)
                .header("x-client-token", token)
                .contentType(ContentType.JSON)
                .when()
                .post(URL_POST_COMPANY)
                .then()
                .log().all()
                .extract().path("id");
    }

    //Получение информации о компании
    public Company getCompanyInfo(int compId) {
        return given().log().all()
                .pathParam("id", compId)
                .get(URL_ID_COMP)
                .then().log().all()
                .extract().as(Company.class);
    }

    @Step("Создаем тестового сотрудника")
    public Response createEmployee(String firstName, String lastName, String middleName,
                                   int companyId, String email,
                                   String url, String phone, boolean isActive,
                                   String token) {

        CreateEmployee createEmployee = new CreateEmployee(firstName, lastName, middleName, companyId,
                email, url, phone, isActive, token);


        return given()
                .log().all()
                .body(createEmployee)
                .header("x-client-token", token)
                .contentType(ContentType.JSON)
                .when()
                .post(URL_GET_EMPLOYEEID)
                .thenReturn();
    }
}


















