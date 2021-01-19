package com.qa.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ChooseRoom extends PageBase{

    public ChooseRoom(WebDriver driver){
        super(driver);
        waitForElement(By.id("availability-header"), 5);
    }


    


}

