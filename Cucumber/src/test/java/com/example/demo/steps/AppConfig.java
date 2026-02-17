package com.example.demo.steps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${app.url}")
    private String appURL;

    @Value("${browser}")
    private String browser;

    public String getAppURL(){
        return appURL.toString();
    }

    public String getBrowser(){
        return browser.toString();
    }
}
