Feature: WebGroup8 API Home Book List

  @APIHome
  Scenario: API Get list of books successfully
    When I execute the API to get book list
    Then The API should return status code 200 for books
    And The API should return a list containing books
