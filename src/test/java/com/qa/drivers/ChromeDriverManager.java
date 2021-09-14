package com.qa.drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.net.MalformedURLException;


public class ChromeDriverManager extends DriverManager{


    @Override
    protected void createDriverLocal() {
        
        System.setProperty("webdriver.chrome.driver", pathDir+"/src/test/resources/chromedriver92");
        driver = new ChromeDriver();
    }


    @Override
    protected void createDriverRemote(String ipAddress, String port) {
        String hubURL = "http://"+ipAddress+":"+port+"/wd/hub";

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-notifications");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
     
        URL remoteAddress = null;

        try{
            remoteAddress = new URL(hubURL);

        }catch(MalformedURLException e) {
            e.printStackTrace();
        }

        driver = new RemoteWebDriver(remoteAddress, capabilities);

    }

}