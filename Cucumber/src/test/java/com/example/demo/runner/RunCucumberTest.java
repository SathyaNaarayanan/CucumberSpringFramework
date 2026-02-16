package com.example.demo.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "classpath:features",
        glue = {"com.example.demo.steps","com.example.demo.config"},
//      tags = "@BasicCheck or @TestFail",
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/cucumber-reports/cucumber.json"
                },
        //Auto generated report from cucumber - Since Extent reports are used default cucumber reports are commented
        monochrome = true,
        dryRun = false
)

public class RunCucumberTest extends AbstractTestNGCucumberTests {

    static {
        System.setProperty("dataproviderthreadcount", "3");
        System.out.println("RunCucumberTest executing!");
    }

    @DataProvider(parallel=true)
    @Override
    public Object[][] scenarios(){
        Object[][] scenarios = super.scenarios();
        System.out.println("Scenarios detected: " + scenarios.length);
        return scenarios;
    }

}
