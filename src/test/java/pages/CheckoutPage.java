package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.google.common.io.Files;

import java.io.File;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    // Locators
    By billingForm = By.id("billing-new-address-form");
    By firstName = By.id("BillingNewAddress_FirstName");
    By lastName = By.id("BillingNewAddress_LastName");
    By email = By.id("BillingNewAddress_Email");
    By country = By.id("BillingNewAddress_CountryId");
    By city = By.id("BillingNewAddress_City");
    By address = By.id("BillingNewAddress_Address1");
    By zip = By.id("BillingNewAddress_ZipPostalCode");
    By phone = By.id("BillingNewAddress_PhoneNumber");
    By billingContinue = By.cssSelector("#billing-buttons-container .new-address-next-step-button");

    By shippingContinue = By.cssSelector("#shipping-buttons-container .new-address-next-step-button");

    By shippingMethodContinue = By.cssSelector("#shipping-method-buttons-container .shipping-method-next-step-button");
 // ✅ Stable Locators
    By paymentMethodContinue = By.cssSelector("#payment-method-buttons-container .payment-method-next-step-button");
    By paymentInfoContinue = By.cssSelector("#payment-info-buttons-container .payment-info-next-step-button");
    By confirmBtn = By.cssSelector("#confirm-order-buttons-container .confirm-order-next-step-button");

    By successMsg = By.cssSelector(".section.order-completed .title");

    By guestCheckoutBtn = By.cssSelector("input.button-1.checkout-as-guest-button");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    public void fillBilling() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(billingForm));
            System.out.println("📝 Filling billing form...");

            if (driver.findElements(firstName).size() > 0) {
                driver.findElement(firstName).clear();
                driver.findElement(firstName).sendKeys("Maazid");

                driver.findElement(lastName).clear();
                driver.findElement(lastName).sendKeys("Shaik");

                driver.findElement(email).clear();
                driver.findElement(email).sendKeys("maazid@example.com");

                WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(country));
                new Select(countryDropdown).selectByVisibleText("India");
                Thread.sleep(1000); // Wait for city/address load if dynamic

                driver.findElement(city).clear();
                driver.findElement(city).sendKeys("Hyderabad");

                driver.findElement(address).clear();
                driver.findElement(address).sendKeys("123 Road");

                driver.findElement(zip).clear();
                driver.findElement(zip).sendKeys("500001");

                driver.findElement(phone).clear();
                driver.findElement(phone).sendKeys("9876543210");

                System.out.println("✅ Billing details filled.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Billing form skipped or failed: " + e.getMessage());
        }
    }

    // Generic scroll + JS click helper
    private void scrollAndClick(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            
            // Retry logic for clicking
            for (int i = 0; i < 2; i++) {
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    System.out.println("✅ Clicked using JS: " + locator);
                    break;
                } catch (Exception clickEx) {
                    System.out.println("🔁 Retry clicking " + locator + ": " + clickEx.getMessage());
                    Thread.sleep(1000);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Failed scrollAndClick: " + locator + " => " + e.getMessage());
        }
    }

    // Retry scroll + click logic (used for Billing step)
    public void retryScrollAndClick(By locator) {
        for (int i = 0; i < 3; i++) {
            try {
                scrollAndClick(locator);
                return;
            } catch (Exception e) {
                System.out.println("🔁 Retry " + (i + 1) + " for: " + locator);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ignored) {}
            }
        }
        System.out.println("❌ Failed after 3 attempts to click " + locator);
    }

    public void clickGuestCheckoutIfVisible() {
        try {
            WebElement guestBtn = wait.until(ExpectedConditions.elementToBeClickable(guestCheckoutBtn));
            guestBtn.click();
            System.out.println("✅ Clicked on 'Checkout as Guest'");
        } catch (TimeoutException e) {
            System.out.println("ℹ️ Guest checkout not shown — maybe already logged in.");
        }
    }

    public void continueFromBilling() {
        try {
            System.out.println("➡️ Trying to continue from Billing...");
            
            // Screenshot before click
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src, new File("billing-before-click.png"));

            // Alert handling
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
                System.out.println("⚠️ Alert dismissed.");
            } catch (NoAlertPresentException ignored) {}

            scrollAndClick(billingContinue);

            // Wait for Billing form to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(billingForm));
            System.out.println("✅ Billing completed.");

        } catch (Exception e) {
            System.out.println("❌ Billing continue failed: " + e.getMessage());
        }
    }


    private void captureFormErrors() {
        try {
            java.util.List<WebElement> errorMessages = driver.findElements(By.cssSelector(".field-validation-error"));
            if (!errorMessages.isEmpty()) {
                System.out.println("❌ Form validation errors found:");
                for (WebElement error : errorMessages) {
                    System.out.println("⚠️ " + error.getText());
                }
            } else {
                System.out.println("✅ No visible form errors.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not capture form errors: " + e.getMessage());
        }
    }


	public void continueFromShippingAddress() {
        scrollAndClick(shippingContinue);
    }

    public void continueFromShippingMethod() {
        scrollAndClick(shippingMethodContinue);
    }

    public void continueFromPaymentMethod() {
        scrollAndClick(paymentMethodContinue);
    }

    public void continueFromPaymentInfo() {
        scrollAndClick(paymentInfoContinue);
    }

    public void confirmOrder() {
        scrollAndClick(confirmBtn);
    }

    public boolean isSuccessPage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).isDisplayed();
        } catch (Exception e) {
            System.out.println("❌ Order success page not found: " + e.getMessage());
            return false;
        }
    }
}
