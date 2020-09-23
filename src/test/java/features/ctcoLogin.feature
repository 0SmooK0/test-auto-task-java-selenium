@After @Before
Feature: Line counter
	  Verify that a vacancy has exact amount of skills listed

  Scenario Outline: Check if <VACANCY>'s skill amount match <LINECOUNT> under <LIST>
    Given user is on homepage "https://ctco.lv/en"
    When user navigates to <MENU> and submenu <SUBMENU>
    And user navigates to <VACANCY>
    Then count exactly <LINECOUNT> of lines under <LIST>

    Examples: 
      | MENU      | SUBMENU     | VACANCY                    | LIST                                                    | LINECOUNT |
      | "CAREERS" | "VACANCIES" | "Test Automation Engineer" | "Professional skills and qualification:"                |         5 |
      | "CAREERS" | "VACANCIES" | "Test Automation Engineer" | "As an advantage:"                                      |         3 |
      | "CAREERS" | "VACANCIES" | "Test Automation Engineer" | "Responsibilities (may vary depending on title level):" |         5 |
      | "CAREERS" | "VACANCIES" | "Test Automation Engineer" | "Benefits:"                                             |         6 |