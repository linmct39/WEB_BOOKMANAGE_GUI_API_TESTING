Feature: WebGroup8 UI Register

  Background: open web page
    Given I open web page WebGroup8
    And I click on register link

  @UIRegister @valid
  Scenario Outline: Test UI Register with valid data
    When I register with name "<name>", username "<username>", email "<email>", password "<password>" and confirm password "<confirmPassword>"
    Then I verify title page is "Đăng nhập"

    Examples:
      | name      | username | email               | password | confirmPassword |
      | User Test | usertest | newuser@gmail.com   | 123456   | 123456          |

  @UIRegister @invalid
  Scenario Outline: Test UI Register with empty required fields (TC_REG_03)
    When I register with name "<name>", username "<username>", email "<email>", password "<password>" and confirm password "<confirmPassword>"
    Then The system should display a required field validation message

    Examples:
      | name      | username | email               | password | confirmPassword |
      |           | usertest | test@gmail.com      | 123456789   | 123456789        |


  @UIRegister @invalid
  Scenario Outline: Test UI Register with existing email (TC_REG_04)
    When I register with name "<name>", username "<username>", email "<email>", password "<password>" and confirm password "<confirmPassword>"
    Then The system should display an email exists error message

    Examples:
      | name      | username | email                  | password | confirmPassword |
      | User Test | usertest | linh_admin@gmail.com   | 123456   | 123456          |
