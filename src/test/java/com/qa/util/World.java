package com.qa.util;


import org.openqa.selenium.WebDriver;
import com.qa.util.DriverFactory;
import com.qa.drivers.DriverManager;
import com.qa.util.Browser;


public class World{

    public DriverManager driverManager;
    public WebDriver driver;
    public Browser browser;


    public World(){
        browser = Browser.CHROME;
        driverManager = DriverFactory.setDriver(browser);
        driver = driverManager.getDriver();
    }


}