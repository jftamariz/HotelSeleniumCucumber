package com.qa.util;


import com.qa.drivers.ChromeDriverManager;
import com.qa.drivers.FirefoxDriverManager;
import com.qa.drivers.DriverManager;

public class DriverFactory {

    public static DriverManager setDriver(Browser browser){

        DriverManager driverManager;

        switch(browser){

            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                //throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }

        return driverManager;
    }


}