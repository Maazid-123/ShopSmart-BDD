package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import utils.BaseClass;

public class CheckoutSteps {
    CartPage cart = new CartPage(BaseClass.driver);
    CheckoutPage checkout = new CheckoutPage(BaseClass.driver);

    @And("user proceeds to checkout")
    public void user_goes_to_checkout() {
        cart.proceedToCheckout();
        checkout.clickGuestCheckoutIfVisible(); // ✅ handle guest checkout
    }


    @Then("user should see the checkout page")
    public void checkout_page_loaded() {
        // next call waits reliably in fillBilling
        checkout.fillBilling();
    }

    @And("user continues from billing address")
    public void billing_continue() {
        checkout.continueFromBilling();
    }

    @And("user continues from shipping address")
    public void shipping_address_continue() {
        checkout.continueFromShippingAddress();
    }

    @And("user continues from shipping method")
    public void shipping_method_continue() {
        checkout.continueFromShippingMethod();
    }

    @And("user continues from payment method")
    public void payment_method_continue() {
        checkout.continueFromPaymentMethod();
    }

    @And("user continues from payment information")
    public void payment_info_continue() {
        checkout.continueFromPaymentInfo();
    }

    @And("user confirms the order")
    public void confirm_order() {
        checkout.confirmOrder();
    }

    @Then("user should see order success message")
    public void see_success_msg() {
        Assert.assertTrue("Order wasn't successful", checkout.isSuccessPage());
        BaseClass.closeBrowser();
    }
}
