package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;
    By cartLink = By.linkText("Shopping cart");
    By termsCheckbox = By.id("termsofservice");
    By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) { this.driver = driver; }

    public void proceedToCheckout() {
        driver.findElement(cartLink).click();
        driver.findElement(termsCheckbox).click();
        driver.findElement(checkoutButton).click();
    }
}
