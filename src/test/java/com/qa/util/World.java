package com.qa.util;


import org.openqa.selenium.WebDriver;
import com.qa.util.DriverFactory;
import com.qa.drivers.DriverManager;
import java.time.LocalDate;


public class World{

    public DriverManager driverManager;
    public WebDriver driver;
    public Browser browser;


    public World(){
        browser = Browser.CHROME;
        driverManager = DriverFactory.setDriver(browser);
        driver = driverManager.getDriver();
    }

    public int getDayOfTheMonth(){
        LocalDate currentdate = LocalDate.now();
        return currentdate.getDayOfMonth();
    }

    public String getMonthOfTheYear(){
        LocalDate currentdate = LocalDate.now();
        return currentdate.getMonth().toString();
    }

    public LocalDate getDatePlushDays(int plusDays){
        return LocalDate.now().plusDays(plusDays);
    }
}