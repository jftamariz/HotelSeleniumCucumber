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


public class PackagesFlights extends PageBase{

    private By btnFlightHotel = By.id("tab-package-tab-hp");
    private By inputAirportCity = By.id("package-origin-hp-package");

    public PackagesFlights(World world){
        super(world);
        waitForElement(btnFlightHotel, 5);
    }

    public WebElement getHeaderMenuItemByName(String menuItem){
        WebElement header = waitForElement(By.id("menu-bar"), 5);

        List<WebElement> menuItems = getElements(header, By.xpath("//a[@class='menuitem clickreport ']"));

        WebElement found = menuItems.stream()
            .filter(item -> item.getText().equalsIgnoreCase(menuItem))
            .collect(Collectors.toSet())
            .stream()
            .findFirst()
            .get();

        return found;
    }



    public void writeCityOrAirport(String cityOrAirport){
        WebElement inputCityOrAirport = getElement(By.id("package-origin-hp-package"));
        inputCityOrAirport.clear();
        inputCityOrAirport.sendKeys(cityOrAirport);

        WebElement listResult = waitForElement(By.id("typeaheadDataPlain"));
        getElements(listResult, By.className("results-item")).get(0).click();
    }
    
    public void writeDestination(String destination){
        WebElement inputDestination = getElement(By.id("package-destination-hp-package"));
        inputDestination.clear();
        inputDestination.sendKeys(destination);

        WebElement listResult = waitForElement(By.id("typeaheadDataPlain"));
        getElements(listResult, By.className("results-item")).get(0).click();
    }
    
    public void writeDepartureDate(String departureDate){
        WebElement inputDestination = getElement(By.id("package-departing-hp-package"));
        inputDestination.clear();
        inputDestination.sendKeys(departureDate);
    }
    
    public void writeReturnDate(String returnDate){
        WebElement inputDestination = getElement(By.id("package-returning-hp-package"));
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

    }
    
    public void selectAirlineClass(String airlineClass){
        Select select = new Select(getElement(By.id("package-advanced-preferred-class-hp-package")));
        select.selectByVisibleText(airlineClass);
    }
        
    public SearchResult clickSearch(){
        getElement(By.id("search-button-hp-package")).click();
        waitForElementDisappear(By.id("interstitial-message"), 40);
        return new SearchResult(world);
    }
}