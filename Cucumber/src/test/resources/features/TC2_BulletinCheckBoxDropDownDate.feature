Feature: TC2_Bulletin_CheckBox_DropDown_date

  @BasicCheck
  Scenario Outline: TC2_Bulletin_CheckBox_DropDown
    Given Launch Automation pratice page
    Then verify GUI Element text link
    And I add "<Name>" Name
    And I select Gender "<Gender>"
    And I select country as "India"
    And I select the color "<color>"
    And I select curren date
    Examples:
      | Gender | color | Name  |
      | Male   | Green | Testy |

