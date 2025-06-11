package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.id("small-searchterms");
    By searchButton = By.cssSelector("input.button-1.search-box-button");
    By firstProduct = By.cssSelector(".product-item .product-title a");
    By addToCartButton = By.cssSelector("input.button-1.add-to-cart-button");
    By successMessage = By.cssSelector("div.bar-notification.success");

    public void searchProduct(String productName) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void selectFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }
}
