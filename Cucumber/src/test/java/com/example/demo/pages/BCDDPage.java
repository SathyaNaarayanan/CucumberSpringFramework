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
@Component
@ScenarioScope
public class BCDDPage extends BasePage{


    public BCDDPage(DriverManager driverManager, UiActions uiAction) {
        super(driverManager, uiAction);
    }


    @FindBy(how= How.ID, using="male")
    public WebElement genderMale;

    public void selectGender(String v) throws InterruptedException {
        uiAction.scrollTOE(genderMale);
        if(v.equalsIgnoreCase("male")){
            genderMale.click();
        }
    }

    @FindBy(how=How.ID, using="country")
    protected WebElement countrySelect;

    @FindBy(how=How.ID, using="colors")
    protected WebElement colorsSelect;

    @FindBy(how=How.ID, using="datepicker")
    protected WebElement datePicker1;

    @FindBy(how=How.XPATH, using="//a[contains(@class,'ui-state-highlight')]")
    protected WebElement currentDateSelection;


    public void selectCountry(String v){
        //uiAction.scrollToElement(countrySelect);
        uiAction.dropDownSelect(countrySelect, v);
    }

    public void selectColor(String v){
        //uiAction.scrollToElement(colorsSelect);
        uiAction.dropDownSelect(colorsSelect, v);
    }

    public void selectDatePicker1(){
        //uiAction.scrollToElement(datePicker1);
        datePicker1.click();
        currentDateSelection.click();
    }

}
