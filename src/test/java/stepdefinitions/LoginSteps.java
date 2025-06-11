package stepdefinitions;



import io.cucumber.java.en.*;
import pages.LoginPage;
import utils.BaseClass;
import utils.ExcelUtils;

import java.util.List;
import java.util.Map;

public class LoginSteps {

    LoginPage loginPage;
    List<Map<String, String>> testData;

    @Given("user is on the login page")
    public void user_is_on_login_page() {
        BaseClass.launchBrowser();
        BaseClass.driver.get("https://demowebshop.tricentis.com/login");
        loginPage = new LoginPage(BaseClass.driver);

        // Read Excel data
        String filePath = System.getProperty("user.dir") + "/src/test/resources/TestDataEx.xlsx";
        testData = ExcelUtils.readExcelData(filePath, "LoginData");

        String username = testData.get(0).get("Username");
        String password = testData.get(0).get("Password");

        System.out.println("Data from Excel: " + username + " / " + password);

    }

    @When("user enters valid username and password")
    public void user_enters_valid_credentials() {
        String username = testData.get(0).get("Username");
        String password = testData.get(0).get("Password");

        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
    }

    @When("clicks the login button")
    public void clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("user should be redirected to the homepage")
    public void redirected_to_homepage() {
        System.out.println("Login successful. Current URL: " + BaseClass.driver.getCurrentUrl());
        BaseClass.closeBrowser();
    }
}
