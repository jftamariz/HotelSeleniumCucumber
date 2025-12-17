package com.qa.drivers;

import org.openqa.selenium.WebDriver;


public abstract class DriverManager {

    protected WebDriver driver;

    protected abstract void createDriverLocal();
    protected abstract void createDriverRemote(String ipAddress, String port);

    protected static final String pathDir = new java.io.File("").getAbsolutePath();

    public WebDriver getDriver(){
        if(null == driver){

            // createDriverLocal();
            createDriverRemote("localhost", "4444");
        }

        return driver;
    }

    public void quitDriver(){
        if(null != driver){
            driver.quit();
            //driver = null;
        }
    }

}
