package seleniumgluecode;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertSame;
import java.util.List;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestDefinitions {
  public static WebDriver driver;

  @Before //launches the webdriver of your choice - in future need to change it as variable
  public void launch_browser() throws Throwable {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    driver = new ChromeDriver();
    //	System.setProperty("webdriver.gecko.driver","geckodriver.exe");
    //	driver = new FirefoxDriver();
  }

  @Given("user is on homepage {string}")
  public void user_is_on_homepage(String homepage) throws Throwable {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get(homepage);
  }

  @When("user navigates to {string} and submenu {string}")
  public void user_navigates_to_menu(String menu, String submenu) throws Throwable {
    Actions action = new Actions(driver);
    WebElement we = driver.findElement(By.linkText(menu));
    action.moveToElement(we).perform();
    driver.findElement(By.linkText(submenu)).click();
  }

  @When("user navigates to {string}")
  public void user_clicks_link(String link) throws Throwable {
    driver.findElement(By.linkText(link)).click();
  }

  @Then("count exactly {} of lines under {string}") //This step will only work for pages that have CLASS="...fadeIn active" - play around with it at myElementPath
  public void count_lines_in_paragraph(int linec, String skills) {
    String myElementPath = "//*[contains(@class,'fadeIn active')]//*/em[contains(text(),'" + skills + "')]";

    try {
      WebDriverWait wait = new WebDriverWait(driver, 10);
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(myElementPath)));
    } catch(NoSuchElementException e) {}

    List < WebElement > paragraphBr = driver.findElements(By.xpath(myElementPath + "/ancestor::p/following-sibling::*[1]/br"));
    int countBr = paragraphBr.size();
    if (countBr > 0) {
      int compensateBreak = countBr + 1;
      assertSame(linec, compensateBreak);
    }
    else {
      List < WebElement > paragraphLi = driver.findElements(By.xpath(myElementPath + "/ancestor::p/following-sibling::*[1]/*"));
      int countOther = paragraphLi.size();
      assertSame(linec, countOther);
    }
  } //when working with large functions, it's a good idea to move them to a separate file and call them out.

  @After //when working with many hooks, it is recommended to store them in a separate file.
  public void AfterSteps() {
    driver.quit();
  }

}