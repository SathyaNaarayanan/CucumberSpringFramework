Feature: TC1_Input Text box fields

  @TestFail
  Scenario Outline: TC1_Input Text box fields
    Given Launch Automation pratice page
    Then verify GUI Element text link
    And I add "<Name>" Name
    And Test Fail case
    Examples:
      |Name  |
      |Test  |

