Feature: WebGroup8 UI Home Book List

  @UIHome
  Scenario: Check book list displays on home page
    Given I open web page WebGroup8
    And I click on home link
    Then The system should display the list of books
