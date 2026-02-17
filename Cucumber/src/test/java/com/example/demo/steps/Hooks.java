package com.example.demo.steps;

import com.example.demo.libraries.DriverManager;
import com.example.demo.pages.PageInitializer;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



/*@Component - not works
io.cucumber.core.backend.CucumberBackendException: Glue class com.example.Cucumber.steps.Hooks was (meta-)annotated with @Component; marking it as a candidate for auto-detection by Spring.
Glue classes are detected and registered by Cucumber. Auto-detection of glue classes by spring may lead to duplicate bean definitions.
Please remove the @Component (meta-)annotation
 */

/*Hooks are Cucumber-controlled
Constructor injection works because of cucumber-spring
Driver initialization happens at the right lifecycle point

Hooks do not need @ScenarioScope
They are already executed per scenario

If Hooks is not annotated, Cucumber still creates one instance per scenario
*/

public class Hooks {

    private String url;

    private final DriverManager driver;

    protected PageInitializer pageInitializer;

    public Hooks(DriverManager driver, PageInitializer pageInitializer, AppConfig appConfig) {
        this.driver = driver;
        this.pageInitializer = pageInitializer;
        this.url = appConfig.getAppURL();
    }

    @Before
    public void InitializeTest(Scenario scenario){
        driver.initDriver(); // creates & sets driver for this thread
        driver.getDriver().manage().window().maximize();
        driver.getDriver().navigate().to(url);
        System.out.println("initialize driver");
        pageInitializer.initPages();
    }

    @AfterStep
    public void captureScreenshot(Scenario scenario) {
        try {
            // Capture screenshot as bytes
            byte[] screenshot = ((TakesScreenshot) driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @After
    public void quitDriver(Scenario scenario){
        driver.quitDriver();
    }

}
