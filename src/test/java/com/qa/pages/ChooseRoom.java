package com.qa.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import com.qa.util.World;


public class ChooseRoom extends PageBase{

    public ChooseRoom(World world){
        super(world);
        waitForElement(By.id("availability-header"), 5);
    }


    


}

