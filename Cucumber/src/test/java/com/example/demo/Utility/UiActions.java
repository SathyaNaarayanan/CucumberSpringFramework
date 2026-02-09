package com.example.demo.Utility;
import com.example.demo.libraries.DriverManager;
import com.example.demo.pages.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.function.Supplier;

/*
Singleton bean is fine
Uses driverManager.getDriver() → thread-safe
Actions object is created per call → correct
It is one instance per JVM
Thread safety comes from ThreadLocal<WebDriver>
 */

@Component
public class UiActions {

    private DriverManager driverManager;

    @Autowired
    public UiActions(DriverManager driver){
        this.driverManager = driver;
    }

    public void dropDownSelect(WebElement e, String value){
        new Select(e).selectByVisibleText(value);
    }

    public void scrollToElement(BasePage basePage, Supplier<WebElement> elementSupplier) throws InterruptedException {
//        basePage.initPage();
        WebElement e = elementSupplier.get();
        WebDriver driver = driverManager.getDriver();
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", e);
    }

    public void scrollTOE(WebElement e){
        Actions act = new Actions(driverManager.getDriver());
        act.scrollToElement(e).build().perform();
    }

}


/*
Spring → manages beans & dependency injection

Cucumber → controls scenario lifecycle

Selenium / WebDriver → browser lifecycle

-----

@Configuration + @Bean defines WebDriver creation.

@ScenarioScope ensures one driver per scenario, not singleton.

StepDefinitions and Hooks are Spring beans, so constructor injection and @Autowired both work.

Pages use @Component + @ScenarioScope and are instantiated per scenario.

BasePage uses constructor injection + @PostConstruct to initialize PageFactory safely.

Applying @ScenarioScope across Driver, Pages, Steps, and Hooks prevents conflicts during parallel runs.

---------
   @Component and @Bean - create and mange object for this class
       @Component class base annotation
       @Bean method based annotation
   @Autowired - works only when Spring-manged Beans (Field injection)
   or can use Constructor injection
   @PostConstruct ensure elements are initialized after dependency injection


 */