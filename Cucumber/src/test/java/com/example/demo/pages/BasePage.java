package com.example.demo.pages;

import com.example.demo.Utility.UiActions;
import com.example.demo.libraries.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/*
Abstract → prevents misuse
Constructor injection → parallel saf
PageFactory init after driver creation → correct
Is not managed by Spring but only inherited by spring beans
 */

public abstract class BasePage {

    protected DriverManager driverManager;
    protected WebDriver driver;
    protected UiActions uiAction;


    // Constructor injection is cleaner and parallel-safe
    public BasePage(DriverManager driverManager, UiActions uiAction) {
        this.driverManager = driverManager;
        this.uiAction = uiAction;
    }

    // MUST call this after driver is initialized for the current thread
    public void initPage() {
        this.driver = driverManager.getDriver();
        if (this.driver != null) {
            PageFactory.initElements(driver, this);
        }
    }

}
