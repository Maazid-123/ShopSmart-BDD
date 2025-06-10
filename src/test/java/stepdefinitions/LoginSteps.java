package stepdefinitions;

import io.cucumber.java.en.*;
import pages.LoginPage;
import utils.BaseClass;

public class LoginSteps {

    LoginPage loginPage;

    @Given("user is on the login page")
    public void user_is_on_login_page() {
        BaseClass.launchBrowser();
        BaseClass.driver.get("https://demowebshop.tricentis.com/login");
        loginPage = new LoginPage(BaseClass.driver);
    }

    @When("user enters valid username and password")
    public void user_enters_valid_credentials() {
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("Test1234"); // Use a valid test account
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


