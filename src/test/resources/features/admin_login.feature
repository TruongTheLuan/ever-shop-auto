Feature: Admin - Loin

  Background:
    Given I open admin login page

  Scenario: Verify login success
    When I input "Email" field with value "luanttruongtester@gmail.com"
    And I input "Password" field with value "admin1234"
    And I click on "SIGN IN" button
    Then I should see dashboard page

  Scenario Outline: Verify login form validation
    When I input "Email" field with value <email>
    And I input "Password" field with value <password>
    And I click on "SIGN IN" button
    Then I should see error message for "Email" is <emailError>
    And I should see error message for "Password" is <passwordError>

    Examples:
      | email                         | password    | emailError                    | passwordError                 |
      | ""                            | "admin1234" | "This field can not be empty" | ""                            |
      | "luanttruongtester@gmail.com" | ""          | ""                            | "This field can not be empty" |
      | "luanttruongtester.com"       | "admin1234" | "Invalid email"               | ""                            |
      | ""                            | ""          | "This field can not be empty" | "This field can not be empty" |