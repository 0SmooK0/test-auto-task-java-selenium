# Test Automation Framework
### Selenium, Java, Maven, Cucumber 

This frameowrk is built with the intention to fulfill a test automation task assigned to the author

### PREREQUISITES:

 - JDK (Author is running JDK 8),
 -- when installing JDK, make sure to point your JAVA_HOME Windows Environment variable to the JDK rather than JRE that it tries to point to by default.
 - Maven (Author is using 3.6.3)

### RUNNING TESTS:
  - In terminal run:
```sh
        mvn clean test
```
For the purpose of this framework, before mentioned command is enough however if you are interested in other Maven commands, take a look at:
https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
Some of them may require additional configuration.

### REPORTS:
Author has set up a HTML and JSON reports through ExtentReports: https://extentreports.com/.
The version used is not the latest but the one that works with Cucumber 6.0 all the while supporting HTML reports. Also due to further compatibility challanges, an adapter was used. The reports are not throughoutly configured and are more set up as a nice-to-have rather than the focus of this task.

- After each run both HTML and JSON report will be placed here:
...\Test Results\Report DD-Sep-YY HH-mm-ss\...
- Report path and what reports are being generated can be changed in: 
...\src\test\resources\extent.properties
- Report configuration can be done in:
...\config\report.xml

##### Known issues:
- Sometimes the HTML report doesn't read correcctly "<>" from test name,
- ... maybe more.

##### Potential improvements:
- Set up configuration so all of it can be done from a single file rather than from ...\extent.properties and ...\report.xml,
- Report is dividing tests based on tags, this results in that @Before and @After are taken as separate tests. Potential solutions:
-- Configure reports so they ignore @Before and @After tags,
-- If current report structure to be kept, tests also need to be tagged appropriately to make it more readable.

### TEST SETUP:
Author has chosen to use Cucumber to increase flexibility of created steps. With current implementation, it's possible to easily navigate to other elements and check amount of lines in paragraph after a keyword just by changing or adding Gherkins steps.
Tests are executed through the Runner: (.\src\test\java\runner\Test.java) which references both Feature (test scenarios) and Gluecode (step definitions) file location.
- Read more about Gherkins and Step definitions here: https://cucumber.io/docs/cucumber/api/
- To change the browser for testing, go to step definition file (.\src\test\java\seleniumgluecode\TestDefinitions.java) and find @Before tag where you can uncomment Firefox webdriver or add your own.

##### Limitations of current implementation of 'count exactly {} of lines under {string}' step:
- Only paragraphs with child elements such as 'br' or 'li' will be counted,
- If paragraph for whatever reason contains both 'br' amd 'li' child elements, then only 'br' will be counted,
- In a paragraph divided by 'br' elements, there will be one 'br' fewer than actual lines, as such '+1' is added to that amount - this may give inaccurate results,
- empty breaks/lines of paragraps will also be counted,
- ...more?

##### Potential improvements:
- Each test launches and closes webdriver instead of reusing existing - rework driver launch steps so it is exectuted once in the Scenario Outline cycle
- Or keep as is if some form of parallel testing like Selenium Grid is planned to be used.
- Set the Browser choice as a variable somewhere - either using TestNG or so it's configurable in the runner file.
- Add @tags to better organize tests in future.
- Improve Failure/Error handling

### DEPENDENCIES:
Author has set dependencies in .\pom.xml
