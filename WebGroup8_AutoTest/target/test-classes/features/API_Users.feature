Feature: WebGroup8 API Home Book List
  @APIUsers
  Scenario: API Get list of users successfully
    When I execute the API to get users list
    Then The API should return status code 200 for users
    And The API should return a list containing users
