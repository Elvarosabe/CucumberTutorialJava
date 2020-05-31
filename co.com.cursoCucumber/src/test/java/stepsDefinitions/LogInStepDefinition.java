package stepsDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import baseUtil.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogInStepDefinition extends BaseUtil {

  private WebDriver webdriver;
  private BaseUtil base;
  private String PATHDRIVER = "src/test/resources/chromedriver/";

  public LogInStepDefinition(BaseUtil baseUtil) {
    this.base = baseUtil;
  }

  @Before()
  public void beforeSetup() {
    // UTILIZO ESTE HOOK PARA TODA LA CONFIGURACION INICIAL
    // ESTE HOOK SE VA A EJECUTAR ANTES DEL PRIMER STEP DE CADA ESCENARIO

    System.setProperty("webdriver.chrome.driver", PATHDRIVER + "chromedrivernew.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    webdriver = new ChromeDriver(options);
  }

  @Given("^I am in the  login page of the paraBank Application$")
  public void i_am_in_the_login_page_of_the_paraBank_Application() throws Throwable {
    webdriver.get("https://parabank.parasoft.com/parabank/index.htm");
  }

  @When("^I enter valid credentials$")
  public void i_enter_valid_credentials(DataTable table) throws Throwable {

    // List<String> loginForm = table.asList(String.class);
    List<String> loginForm = table.asList();

    base.userfullName = loginForm.get(2); // DE ESTA FORMA ESTOY DANDOLE ESE VALOR A LA VARIABLE QUE HAY EN MI CLASE
                                          // BASE Y LA CUAL QUIERO COMPARTIR CON OTROS STEPS

    webdriver.findElement(By.name("username")).sendKeys(loginForm.get(0));
    webdriver.findElement(By.name("password")).sendKeys(loginForm.get(1));
    webdriver.findElement(By.name("username")).submit();
  }

  @Then("^I should be  taken to the Overview page$")
  public void i_should_be_taken_to_the_Overview_page() throws Throwable {

    // EN ESTE PUNTO QUE ES OTRO STEP, PUEDO USAR EL VALORQ QUE ASIGNE DESDE UN STEP PREVIO Y HACER USO DEL
    // DEPENDCY INJECTION
    System.out.println("El nombre del usuario con el que intente entrar es " + base.userfullName);
  }

  @After()
  public void afterSetUp() {
    // UTILIZO ESTE HOOK PARA LO QUE DESEO QEU SE HAGA DESPUES DE QUE SE EJECUTEN MIS PASOS DEL STEP DEFINITION
    // ESTE HOOK COORE DESPUES DEL ULTIMO STEP DE CADA ESCENARIO
    webdriver.quit();
  }

}
