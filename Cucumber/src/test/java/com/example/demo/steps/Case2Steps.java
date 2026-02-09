package com.example.demo.steps;

import com.example.demo.pages.BCDDPage;
import io.cucumber.java.en.And;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class Case2Steps {

    private BCDDPage bcdd;
    public Case2Steps(BCDDPage bcdd){
        this.bcdd = bcdd;
//        this.bcdd.initPage();
    }

    @And("I select Gender {string}")
    public void iSelectGender(String v) throws InterruptedException {
        bcdd.selectGender(v);
    }

    @And("I select country as {string}")
    public void iSelectCountryAs(String v) {
        bcdd.selectCountry(v);
    }

    @And("I select the color {string}")
    public void iSelectTheColor(String v) {
        bcdd.selectColor(v);
    }

    @And("I select curren date")
    public void iSelectCurrenDate() {
        bcdd.selectDatePicker1();
    }

}
