Feature: Admin - Loin

  Background:
    Given I open admin login page

  Scenario: Verify login success
    When I input "Email" field with value "$ADMIN_EMAIL$"
    And I input "Password" field with value "$ADMIN_PASSWORD$"
    And I click on "SIGN IN" button
    Then I should see dashboard page

  @login_with_error_500
  Scenario: Verify login with error 500
    When I input "Email" field with value "$ADMIN_EMAIL$"
    And I input "Password" field with value "$ADMIN_PASSWORD$"
    And I click on "SIGN IN" button
    Then I should see dashboard page
    Then I wait for 20000 ms

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