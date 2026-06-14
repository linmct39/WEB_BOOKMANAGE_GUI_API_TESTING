Feature: WebGroup8 UI Search Book

  Background: open web page
    Given I open web page WebGroup8
    And I click on search navigation link

  @UISearch @valid
  Scenario Outline: Test UI Search with exact book name 
    When I enter keyword "<keyword>" into search box
    And I click on the search button
    Then The system should display book containing title "<expectedTitle>"

    Examples:
      | keyword    | expectedTitle |
      | Clean Code | Clean Code    |

  @UISearch @valid
  Scenario Outline: Test UI Search with case insensitive book name (TC_SEA_02)
    When I enter keyword "<keyword>" into search box
    And I click on the search button
    Then The system should display book containing title "<expectedTitle>"

    Examples:
      | keyword    | expectedTitle |
      | CLEAN CODE | Clean Code    |
