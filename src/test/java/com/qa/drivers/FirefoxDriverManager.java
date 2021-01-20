package com.qa.drivers;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {


    @Override
    protected void createDriverLocal() {
        System.setProperty("webdriver.gecko.driver", pathDir+"/src/test/resources/geckodriver");
   
        System.out.println(" Firefox - Local Driver");
        driver = new FirefoxDriver();
    }

    @Override
    protected void createDriverRemote(String ipAddress, String port) {
        System.out.println(" Firefox - Remote Driver");
    }
}