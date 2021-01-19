package com.qa.pages;

import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;



public abstract class PageBase{
    protected WebDriver driver;


    public PageBase(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getElement(By findElementBy){
        try{
            return driver.findElement(findElementBy);
        }catch(NoSuchElementException e){
      
            System.out.println(" [Error] - Return NULL - NoSuchElementException in getElement: "+ findElementBy.toString());
            return null;
        }
    }

    public WebElement getElement(WebElement element, By findElementBy){
        try{
            return element.findElement(findElementBy);

        }catch(NoSuchElementException e){
        
            System.out.println(" [Error] - Return NULL - NoSuchElementException in getElement: "+ findElementBy.toString());
            
            return null;
        }
    }


    public WebElement waitForElement(final By findElementBy) {
        return (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(findElementBy));
    }
    public WebElement waitForElement(final By findElementBy, int timeout){
        try{
            return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.elementToBeClickable(findElementBy));
        }catch(TimeoutException e){
            System.out.println(" [Warn] - Timeout exception in class "+getClass().getName()+" --> waitForElement() for locator: " + findElementBy.toString());
            return null;
        }

    }
    public WebElement waitForElementClickable(WebElement element, By findElementBy, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.elementToBeClickable(element.findElement(findElementBy)));
        }catch(NoSuchElementException e){
            System.out.println(" [Warn] - No Such Element in class "+getClass().getName()+" --> waitForElementClickable() - "+findElementBy.toString());
        }catch(TimeoutException e){
            System.out.println(" [Warn] - Timeout exception in class "+getClass().getName()+" --> waitForElementClickable()");
        }

        return null;
    }


    public List<WebElement> getElements(final By findElementBy){
        List<WebElement> elements = null;
        try{
            elements = driver.findElements(findElementBy);
        }catch(NoSuchElementException e){
            //System.out.println(" [Error] - NoSuchElementException in getElements() looking for "+ findElementBy.toString());
            return null;
        }catch(StaleElementReferenceException e){
            //System.out.println(" {Error] - StaleElementReferenceException in getElements() looking for "+ findElementBy.toString());
            return null;
        }

        return elements;
    }

    public List<WebElement> getElements(final WebElement element, By findElementBy){
        List<WebElement> elements = null;
        try{
            elements = element.findElements(findElementBy);
        }catch(NoSuchElementException e){
            //System.out.println(" [Error] - NoSuchElementException in getElements() looking for "+ findElementBy.toString());
            return null;
        }catch(StaleElementReferenceException e){
            //System.out.println(" {Error] - StaleElementReferenceException in getElements() looking for "+ findElementBy.toString());
            return null;
        }

        return elements;
    }


    public Boolean waitForElementDisappear(final By findElementBy, int timeout){
        try{
            return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.invisibilityOfElementLocated(findElementBy));

        }catch(TimeoutException e){
            System.out.println(" [Error] - Timeout exception waitForElementDisappear() for locator: " + findElementBy.toString());
            return false;

        }
    }




    public void goSleep(int secs){
        try {
            Thread.sleep(secs*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}