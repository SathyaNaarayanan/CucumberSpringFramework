package com.example.demo.steps;

import com.example.demo.pages.InputTextPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;
import org.testng.Assert;

/*
@ScenarioScope = one instance per scenario
Constructor injection is best practice
 */
@ScenarioScope
public class InputfieldSteps {

    private InputTextPage inputTextpage;

    public InputfieldSteps(InputTextPage inputTextpage){
        this.inputTextpage = inputTextpage;
//        this.inputTextpage.initPage();
    }


    @Given("Launch Automation pratice page")
    public void LaunchAutomationPracticePAge(){
        System.out.println("I enter step 1");
    }

    @Then("verify GUI Element text link")
    public void VerifyGUITextLink(){
        System.out.println("I enter step 2");
    }

    @And("I add {string} Name")
    public void IAddName(String value) throws InterruptedException {
//        inputTextpage.initPage();
        inputTextpage.inputName(value);
    }

    @And("I add {string} email")
    public void IAddEmail(String value) throws InterruptedException {
        inputTextpage.inputEmail(value);
    }

    @And("I add {string} phoneNumber")
    public void IAddPhoneNum(String value) throws InterruptedException{
        inputTextpage.inputPhone(value);
    }

    @And("I add {string} address")
    public void IAddAddress(String value) throws InterruptedException{
        inputTextpage.inputAddress(value);
    }

    @And("Test Fail case")
    public void TestFailCase() {

//        Assert.fail("Force Fail the case");
    }

}
