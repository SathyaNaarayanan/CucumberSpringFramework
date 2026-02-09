package com.example.demo.libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/*DriverManager (ThreadLocal, Spring-managed)
Spring is managing DriverManager
WebDriver lives inside ThreadLocal, created manually

Why static?
Ensures one ThreadLocal per class
All Spring beans reference the same thread map
Prevents accidental multiple ThreadLocals

Without static:
Each DriverManager instance gets its own ThreadLocal
Parallel bugs can appear in complex contexts

Why private?
Encapsulation
Prevents misuse (set() from outside)
 */
@Component
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    // Get driver for current thread
    public WebDriver getDriver(){
        return driverThread.get();
    }

    //set driver for current Thread
    public void setDriver(WebDriver driver){
        driverThread.set(driver);
    }

    // Quit driver for current thread
    public void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
    @Value("${browser:chrome}")
    private String browser;

    // Initialize new driver instance for current thread
//    Each thread gets its own real driver, no Spring proxy
    public WebDriver initDriver() {
        WebDriver driver = new ChromeDriver(); // Can customize options here
        setDriver(driver);
        return driver;
    }

}


/*
✅ How This Works
ThreadLocal<WebDriver> stores one driver per thread.

Each parallel test (running in its own thread) will get a separate ChromeDriver.

The static class is still singleton in structure, but drivers are thread-local, so no collisions.

quitDriver() ensures proper cleanup per thread.

3️⃣ Usage in Hooks
public class Hooks {

    private final WebDriver driver;

    public Hooks() {
        this.driver = DriverManager.getDriver();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver(); // quits only this thread's driver
    }
}
Each scenario/thread gets its own driver, even if you don’t use Spring scopes.

Parallel-safe ✅

4️⃣ Key Notes
ThreadLocal is the canonical way to make static singletons thread-safe in test automation.

You must call driver.remove() after quitting, otherwise you risk memory leaks.

Works with JUnit, TestNG, or Cucumber in parallel mode.

No need for @Configuration, @Bean, or @ScenarioScope.

If you want, I can write a full working example with Hooks + PageObjects + ThreadLocal singleton ready for parallel Cucumber execution.

Do you want me to do that?

Is this conversation helpful so far?

 */

 /*@Value("${browser:chrome}")
    private String browser;

    @ScenarioScope
    @Bean(destroyMethod = "quit")
    public WebDriver webDriver(){
        WebDriver driver = null;
        if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }
        return driver;

    }
    Notes:
    this code can be used if no usage of Selenium Action class. Because it requires real RemoteWebDriver.
    here SPRING is wrapping your driver in a proxy and WebDriver is managed by Spring. is likely a proxy object.
     */