package com.qa.util;

import java.util.HashMap;
import java.util.Map;

public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    SAFARI("safari"),
    IE("ie");


    private final String browser;
    private static Map<String, Browser> browserByString = new HashMap<String, Browser>();

    static{
        for(Browser browser : Browser.values()){
            browserByString.put(browser.getBrowser(), browser);
        }
    }

    Browser(String browser){
        this.browser = browser.toLowerCase();
    }

    public String getBrowser(){
        return browser;
    }

    public static Browser getBrowserEnum(String b) throws Exception{
        Browser targetBrowser = browserByString.get(b.toLowerCase());
        if(targetBrowser == null){
            throw new Exception("  Targeted Browser - "+ b+" is not recognized -  Provide another Browser type in Command Line or under config.properties file.");
        }
        return targetBrowser;
    }

    @Override
    public String toString(){
        return browser;
    }

}
