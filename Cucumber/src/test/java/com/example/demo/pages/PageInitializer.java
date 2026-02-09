package com.example.demo.pages;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/*
@Component -> Registers the class with Spring
@ScenarioScope -> Controls the lifecycle of the bean. One instance per Cucumber scenario
@Component registers the bean, @ScenarioScope controls how many instances are created
Thread safety comes from ThreadLocal<WebDriver>
 */
@ScenarioScope
@Component
public class PageInitializer {

    private final List<BasePage> pages;

    public PageInitializer(InputTextPage inputTextPage, BCDDPage bcddpage) {
        this.pages = Arrays.asList(
                inputTextPage,
                bcddpage);
    }

    public void initPages() {
        pages.forEach(BasePage::initPage);
    }
}
