Feature: WebGroup8 UI Cart

  Background: open web page
    Given I open web page WebGroup8
    And I click on home link

  @UICart @valid
  Scenario: Add product to cart and verify in cart (TC_CRT_01 & TC_CRT_02)
    When I click on product "FastAPI Essentials"
    And I increase quantity to 3 on detail page
    And I click add to cart button
    Then The cart badge should display "3"
    When I open the cart
    Then The cart should contain product "FastAPI Essentials" with quantity "3" and total "885.000 đ"
