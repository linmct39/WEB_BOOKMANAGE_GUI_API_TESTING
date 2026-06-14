Feature: WebGroup8 API Register

  @APIRegister @valid
  Scenario Outline: API Register with valid data
    When I execute the API to register with name "<name>", username "<username>", email "<email>" and password "<password>"
    Then The API should return status code 201 for register

    Examples:
      | name      | username | email               | password |
      | User Test | usertest | newuser@gmail.com   | PASSwordco5kituvakitudacbiet@  |

  @APIRegister @invalid
  Scenario Outline: API Register with empty required fields (TC_REG_03)
    When I execute the API to register with name "<name>", username "<username>", email "<email>" and password "<password>"
    Then The API should reject the request with status code 400 for empty fields

    Examples:
      | name      | username | email               | password |
      |           | usertest | test@gmail.com      | 1234567890 |
      | User Test |          |                     | 1234567890   |

  @APIRegister @invalid
  Scenario Outline: API Register with existing email (TC_REG_04)
    When I execute the API to register with name "<name>", username "<username>", email "<email>" and password "<password>"
    Then The API should reject the request with status code 400 for existing email

    Examples:
      | name      | username | email                  | password    |
      | User Test | usertest | linh_admin@gmail.com   | Admin@12345 |
