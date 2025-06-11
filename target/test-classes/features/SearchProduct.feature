Feature: Product Search, Add to Cart and Checkout

  Scenario: Checkout after adding product to cart
    Given user is on the homepage
    When user searches for product from Excel
    And user adds the first product to the cart
    And user proceeds to checkout
    Then user should see the checkout page
    And user continues from billing address
    And user continues from shipping address
    And user continues from shipping method
    And user continues from payment method
    And user continues from payment information
    And user confirms the order
    Then user should see order success message
