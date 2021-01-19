package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import com.qa.util.World;
import java.util.Iterator; 
import java.util.Set; 

public class Home extends PageBase{


    public Home(World world){
        super(world);
        if(!driver.getCurrentUrl().equalsIgnoreCase(("https://www.hotels.com/"))){
            loadPage();
        }
        waitForElement(By.id("qf-0q-destination"));
    }

    public void loadPage(){
        driver.get("https://www.hotels.com/");
    }


    public PackagesFlights goPackagesFlights(){

        getElement(By.id("hdr-packages")).click();
        
        String parent=driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String childWindow=iterator.next();
            if(!parent.equals(childWindow)){
                driver.switchTo().window(childWindow);
            }
        }

        return new PackagesFlights(world);
    }
}