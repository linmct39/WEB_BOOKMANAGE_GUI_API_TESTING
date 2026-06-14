Feature: WebGroup8 UI Login

  Background: open web page
    Given I open web page WebGroup8

  @UILogin @valid
  Scenario Outline: Test UI Login with valid credentials
    When I login with username "<username>" and password "<password>"
    Then I verify title page is "DAW BOOKSTORE — Cửa Hàng Sách Trực Tuyến"
    
    Examples:
      | username             | password |
      | linh_admin@gmail.com | 123456   |

  @UILogin @invalid
  Scenario Outline: Test UI Login with invalid credentials
    When I login with username "<username>" and password "<password>"
    Then The system should display a login error message

    Examples:
      | username             | password      |
      | linh_admin@gmail.com | wrongpass     |
      | wronguser@gmail.com  | 123456        |

