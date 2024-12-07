Feature: Admin - Loin

  Scenario: Verify login success
    Given I open admin login page
    When I input "Email" field with value "luanttruongtester@gmail.com"
    And I input "Password" field with value "admin1234"
    And I click on "SIGN IN" button
    Then I should see dashboard page
