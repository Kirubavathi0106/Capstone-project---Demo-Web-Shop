Feature: Testing the entire flow from adding items to the cart to completing a purchase.

@ShoppingCart
  Scenario: Manage Shopping Cart (Update & Remove)
    Given the user goes to the shopping cart page
    When the user updates the quantity of "Smartphone" to "3"
    And the user removes the "Health Book" from the cart
    And the user clicks the "Update shopping cart" button
    Then the cart should reflect the updated items and quantities
    And user enables the termscheckbox
    And the user selects the checkout button
    
    @OrderPlacement
  Scenario:  Completing an order with valid billing, shipping, and payment details
    Given User is on the Checkout page
    When User enters billing details
    And User selects the shipping method
    And User chooses the payment method and enters details
    And User confirms the order
    Then User should see the order confirmation message
    And User should click continue button