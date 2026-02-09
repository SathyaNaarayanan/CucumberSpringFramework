Feature: TC1_Input Text box fields

  @BasicCheck
  Scenario Outline: TC1_Input Text box fields
    Given Launch Automation pratice page
    Then verify GUI Element text link
    And I add "<Name>" Name
    And I add "<Email>" email
    And I add "<phoneNumber>" phoneNumber
    And I add "<Address>" address
    Examples:
      |Name  |Email|phoneNumber|Address|
      |Test  |test123@choas.com|963214578|NewAddress|

