package stepdefinitions;

import io.cucumber.java.en.*;
import pages.SearchPage;
import utils.BaseClass;
import utils.ExcelUtils;

import java.util.List;
import java.util.Map;

public class SearchSteps {

    SearchPage searchPage;
    List<Map<String, String>> data;

    @Given("user is on the homepage")
    public void user_is_on_the_homepage() {
        BaseClass.launchBrowser();
        BaseClass.driver.get("https://demowebshop.tricentis.com/");
        searchPage = new SearchPage(BaseClass.driver);
    }

    @When("user searches for product from Excel")
    public void user_searches_for_product_from_excel() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/TestDataEx.xlsx";
        data = ExcelUtils.readExcelData(filePath, "SearchData");

        for (Map<String, String> row : data) {
            String searchTerm = row.get("SearchTerm");
            System.out.println("Searching for: " + searchTerm);
            searchPage.searchProduct(searchTerm);
        }
    }

    @And("user adds the first product to the cart")
    public void user_adds_first_product_to_cart() {
        searchPage.selectFirstProduct();
        searchPage.clickAddToCart();
    }
}
