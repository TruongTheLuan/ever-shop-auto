Feature: New product

  Scenario: Verify admin can create new product
    Given I open admin login page
    When I input "Email" field with value "luanttruongtester@gmail.com"
    And I input "Password" field with value "admin1234"
    And I click on "SIGN IN" button
    Then I should see dashboard page
    When I select menu item "New Product"
    Then I should see "New Product" page with "Create A New Product"
    When I input "Name" field with value "hunter"
    #And I input "SKU" field with value "62680"
    And I input a random SKU
    And I input "Price" field with value "100"
    And I input "Weight" field with value "10"
    And I select category "Men" on New Product page
    And I input product description on new product page
    """
      My new product description
    """
    And I set image "upload/Tom_Tom_and_Jerry.png" on new product page
    And I select option "Enabled" of radio "Status"
    And I select option "Visible" of radio "Visibility"
    And I select option "Yes" of radio "Manage stock?"
    And I select option "Yes" of radio "Stock availability"
    And I input "Quantity" field with value "10"
    And I select attribute "Color" with value "Black"
    And I select attribute "Size" with value "XL"
    And I input "Url key" field with value "new_hunter07"
    And I input "Meta title" field with value "Meta title"
    And I input "Meta keywords" field with value "Meta keywords"
    And I click on "Save" button
    Then I should see notification is "Product saved successfully!"
    And I delete product "hunter"

