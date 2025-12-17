package com.qa.pages;

import java.util.stream.Collectors;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import com.qa.util.World;
import org.testng.Assert;


public class PackagesFlights extends PageBase{

    private By btnFlightHotel = By.id("uitk-tabs-button-container");
    private By inputAirportCity = By.id("package-origin-hp-package");

    public PackagesFlights(World world){
        super(world);
        waitForElement(btnFlightHotel, 5);
    }

    public WebElement getHeaderMenuItemByName(String menuItem){
        WebElement header = waitForElement(By.id("uitk-tabs-button-container"), 5);

        List<WebElement> menuItems = getElements(header, By.xpath("//span[@class='uitk-tab-text']"));

        WebElement found = menuItems.stream()
            .filter(item -> item.getText().equalsIgnoreCase(menuItem))
            .collect(Collectors.toSet())
            .stream()
            .findFirst()
            .get();

        return found;
    }



    public void writeCityOrAirport(String cityOrAirport){
        // click on "Leaving From" field to expand input field
        waitForElement(By.xpath("//button[@aria-label='Leaving from']")).click();

        // Write City departure from
        WebElement inputCityOrAirport = waitForElement(By.id("location-field-origin"));
        inputCityOrAirport.clear();
        inputCityOrAirport.sendKeys(cityOrAirport);
        goSleep(1);


        waitForElement(By.xpath("//ul[@data-stid='location-field-origin-results']"));
        // pick the second item result from pop-up result list
        getElements(By.xpath("//li[@data-stid='location-field-origin-result-item']")).get(2).findElement(By.xpath("button/div")).click();
        goSleep(1);
    }
    
    public void writeDestination(String destination){

        // click on the 'Where to' btn to expand input field
        waitForElement(By.xpath("//button[@aria-label='Where to']")).click();

        // wait for input field
        WebElement inputDestination = waitForElement(By.id("destination_form_field"));
        inputDestination.clear();
        inputDestination.sendKeys(destination);

        goSleep(1);
//        waitForElement(By.xpath("//ul[@data-stid='location-field-destination-results']"));

        // pick the second item result from pop-up result list
        getElements(By.xpath("//li[@data-stid='location-field-destination-result-item']")).get(1).findElement(By.xpath(".//button/div")).click();
        goSleep(2);
    }
    public void writeDepartureandReturnDatesForNextMonth(String dayDeparture, String dayReturn){
        if(Integer.parseInt(dayReturn) < Integer.parseInt(dayDeparture)){
            Assert.fail("  -  Please set Return date greater than Departure date");
        }

        getElement(By.id("d1-btn")).click();

        WebElement calendar = getElements(By.xpath("//table[@class='uitk-date-picker-weeks']")).get(1);

        calendar.findElement(By.xpath(".//button[@data-day='"+ dayDeparture+"']")).click();  // Departure Day of the month
        calendar.findElement(By.xpath(".//button[@data-day='"+ dayReturn+"']")).click();  // Return Day of the month

        // Click Done btn
        waitForElement(By.xpath("//button[contains(@class,'dialog-done')]")).click();

    }

    public void writeDepartureDate(String departureDate){
        WebElement inputDestination = getElement(By.id("d1-btn"));
        inputDestination.click();


        goSleep(1);
        WebElement calendar = getElements(By.xpath("//table[@class='uitk-date-picker-weeks']")).get(1);
        List<WebElement> firstDayOfSecondMonthCalendar = calendar.findElements(By.xpath(".//button[@data-day='1']"));
        List<WebElement>  fifthDayOfSecondMonthCalendar = calendar.findElements(By.xpath(".//button[@data-day='5']"));

        firstDayOfSecondMonthCalendar.get(0).click();
        fifthDayOfSecondMonthCalendar.get(0).click();

    }
    
    public void writeReturnDate(String returnDate){
        WebElement inputDestination = getElement(By.id("d2-btn"));
        inputDestination.click();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value = '';", inputDestination);
        inputDestination.sendKeys(returnDate);
    }
    
    public String readNumberOfTravelers(){
        WebElement inputField = getElement(By.id("traveler-selector-hp-package"));
        WebElement inputTextField = getElement(inputField, By.className("inline-amount-titles"));
        return inputTextField.getText();
    }

    
    public void writeNumberOfTravelers(String numOfTravelers){
        // expand Travelers dialog
        getElement(By.id("adaptive-menu")).click();

        //Adults row
        WebElement adults = waitForElement(By.xpath("//div[@data-testid='room-1']"));
        getElement(adults, By.xpath("div[2]/div/button[2]")).click();

        getElement(By.xpath("//button[@data-testid='guests-done-button']")).click();

    }
    
    public void selectAirlineClass(String airlineClass){
        Select select = new Select(getElement(By.id("package-advanced-preferred-class-hp-package")));
        select.selectByVisibleText(airlineClass);
    }
        
    public SearchResult clickSearch(){
        //getElement(By.id("search-button-hp-package")).click();
        waitForElement(By.xpath("//button[@data-testid='submit-button']")).click();
        waitForElementDisappear(By.id("interstitial-message"), 40);
        return new SearchResult(world);
    }
}