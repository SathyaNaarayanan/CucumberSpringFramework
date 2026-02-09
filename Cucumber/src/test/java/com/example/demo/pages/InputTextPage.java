package com.example.demo.pages;
import com.example.demo.Utility.UiActions;
import com.example.demo.libraries.DriverManager;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

/*
@Component registers the bean, @ScenarioScope controls how many instances are created
Thread safety comes from ThreadLocal<WebDriver>
 */
@ScenarioScope
@Component
public class InputTextPage extends BasePage{

    public InputTextPage(DriverManager driverManager, UiActions uiAction) {
        super(driverManager, uiAction);
    }


    @FindBy(how= How.ID,using="name")
    public WebElement nameField;

    @FindBy(how= How.ID,using="email")
    public WebElement emailField;

    @FindBy(how= How.ID,using="phone")
    public WebElement phoneField;

    @FindBy(how= How.XPATH,using="//label[text()=\"Address:\"]/following::textarea[@id=\"textarea\"][1]")
    public WebElement addressField;


    public void inputName(String value) throws InterruptedException {
        nameField.sendKeys(value);
    }

    public void inputEmail(String value) throws InterruptedException {
        emailField.sendKeys(value);
    }

    public void inputPhone(String value) throws InterruptedException {
        phoneField.sendKeys(value);
    }

    public void inputAddress(String value) throws InterruptedException {
        addressField.sendKeys(value);
    }
}
