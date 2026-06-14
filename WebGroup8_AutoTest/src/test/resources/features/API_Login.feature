Feature: WebGroup8 API Login

  @APILogin @valid
  Scenario Outline: API Login with valid credentials
    When I execute the API to login with email "<email>" and password "<password>"
    Then The API should return status code 200

    Examples:
      | email                | password |
      | linh_admin@gmail.com | 123456   |

  @APILogin @invalid
  Scenario Outline: API Login with invalid credentials
    When I execute the API to login with email "<email>" and password "<password>"
    Then The API should reject the request with status code 401

    Examples:
      | email                | password      |
      | linh_admin@gmail.com | wrongpass     |
      | wronguser@gmail.com  | 123456        |
