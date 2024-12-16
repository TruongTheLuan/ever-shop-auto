Feature: Edit - Product
  Background:
    Given I open admin login page

  Scenario: Verify admin can edit a product
    When I input "Email" field with value "luanttruongtester@gmail.com"
    And I input "Password" field with value "admin1234"
    And I click on "SIGN IN" button
    Then I should see dashboard page
    And I create a product by api
    When I select menu item "Products"
    Then I should see "Products" page with "Products"
    When I select product by name "hunt"
    Then I should see "Edit" page with "Editing hunt"
    When I input "Name" field with value "hunt01"
    And I input "SKU" field with value "12"
    And I input "Price" field with value "120"
    And I input "Weight" field with value "100"
    And I input "Quantity" field with value "10"
    And I input "Url key" field with value "new_hunt01"
    And I input "Meta title" field with value "Meta_title01"
    And I input "Meta keywords" field with value "Meta_keywords01"
    And I click on "Save" button
    Then I should see notification is "Product saved successfully!"
    And I delete product "hunt"

