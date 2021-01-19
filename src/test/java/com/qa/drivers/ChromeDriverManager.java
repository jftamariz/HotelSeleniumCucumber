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
        
        System.setProperty("webdriver.chrome.driver", pathDir+"/src/test/resources/chromedriver_87");


        /*
        String dirDownload = pathDir+"/src/test/resources";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", dirDownload);
        chromePrefs.put("download.prompt_for_download", "false");


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("window-size=1350,808");
        options.setExperimentalOption("prefs", chromePrefs);*/

        driver = new ChromeDriver();
        //System.out.println(" \n\n Screen size: " + driver.manage().window().getSize());
    }
    @Override
    protected void createDriverRemote(String ipAddress, String port) {
        System.out.println(" Chrome - Remote Driver");

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