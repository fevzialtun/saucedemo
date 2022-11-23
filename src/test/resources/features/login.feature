Feature: login and E2E test
@wip
  Scenario: Login and End to End Test
    Given The user is on the login page
    When The user is enters "standard_user" and "secret_sauce"
    Then The user should be able to login and see "PRODUCTS" header
    And The user should be able to sort products high to low
    And The user adds two cheapest products in to the basket
    And The user go to the basket
    And The user clicks checkout
    And The user enters details "John" "Snow" "BB101HR"  and finish the purchase

  Then The user verify the total cost