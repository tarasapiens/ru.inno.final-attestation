package ConfigHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {

    private static final String CONFIG_FILE = "src/main/resources/testdata.properties";

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getConnectionStringDB() {
        return properties.getProperty("connectionstringdatabase");
    }

    public static String getUserNameDB() {
        return properties.getProperty("usernamedatabase");
    }

    public static String getPasswordDB() {
        return properties.getProperty("passworddatabase");
    }

    public static String getLoginAdmin() {
        return properties.getProperty("adminloginDB");
    }

    public static String getPasswodAdmin() {
        return properties.getProperty("adminpasswordDB");
    }

    public static String getUrlBase() {
        return properties.getProperty("urlxclients");
    }

    public static String getUrlAuth() {
        return properties.getProperty("urlauthirize");
    }

    public static String getUrlCompanyById() {
        return properties.getProperty("urlgetcompanyid");
    }

    public static String getUrlAddCompany() {
        return properties.getProperty("urladdcompany");
    }

    public static String getUrlDeletedCompany() {
        return properties.getProperty("urldeletedcompany");
    }

    public static String getUrlEmployeeList() {
        return properties.getProperty("urlgetemployeelist");
    }

    public static String getUrlEmployeeById() {
        return properties.getProperty("urlgetemployeebyid");
    }

    public static String getTestNameCompany() {
        return properties.getProperty("testnamecompany");
    }

    public static String getTestDescriptCompany() {
        return properties.getProperty("testdescriptioncompany");
    }

    public static String getTestFirstNameEmpl() {
        return properties.getProperty("testfirstnameemployee");
    }

    public static String getTestLastNameEmpl() {
        return properties.getProperty("testlastnameemployee");
    }

    public static String getTestMiddleNameEmpl() {
        return properties.getProperty("testmiddlenameemployee");
    }

    public static String getTestEmailEmpl() {
        return properties.getProperty("testemailemployee");
    }

    public static String getTestPhoneEmpl() {
        return properties.getProperty("testphoneemployee");
    }
}
