package com.qa.pages;
import com.qa.util.World;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Home extends PageBase{

    private By goingToSearchField = By.cssSelector("button[data-stid='destination_form_field-menu-trigger']");
    private By goingToSearchInput = By.cssSelector("input[data-stid='destination_form_field-menu-input']");
    private By searchResultPullDown = By.cssSelector("[data-stid='destination_form_field-results']");
    private By searchResultItem = By.cssSelector("[data-stid='destination_form_field-result-item-button']");

    private By destinationDates = By.cssSelector("[data-stid='uitk-date-selector-input1-default']");
    private By buttonDone = By.cssSelector("[data-stid='apply-date-selector']");
    private By buttonSubmitSearch = By.id("search_button");
    private By viewNextMonthButton = By.cssSelector("[data-stid='uitk-calendar-navigation-controls-next-button']");
    private By titleMonthSelector = By.xpath("//span[@class='uitk-align-center uitk-month-label']");

    public Home(World world){
        super(world);
        if(!driver.getCurrentUrl().equalsIgnoreCase(("https://www.hotels.com/"))){
            driver.get("https://www.hotels.com/");
        }
        waitForElement(goingToSearchField);
    }

    public PackagesFlights goPackagesFlights(){

        getElement(By.linkText("Packages & Flights")).click();
        waitForElement(By.linkText("Packages")).click();

        /*
        String parent=driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String childWindow=iterator.next();
            if(!parent.equals(childWindow)){
                driver.switchTo().window(childWindow);
            }
        }*/

        return new PackagesFlights(world);
    }

    public void writeGoingToDestination(String destination){
        waitForElement(goingToSearchField).click();
        waitForElement(goingToSearchInput).sendKeys(destination);
        waitForElement(searchResultPullDown);

        // Select first search result from pull down list
        getElements(searchResultItem).get(0).click();
    }

    public void writeDestinationDates(int daysAheadOfDeparture, int daysTripDuration) throws Exception{
        // Expand Calendar 2-months
        waitForElement(destinationDates).click();
        waitForElement(buttonDone,2);

        //  Calculate departure date X dqys ahead
        String monthDeparture = world.getDatePlushDays(daysAheadOfDeparture).getMonth().toString().toLowerCase();

        //  Click next month view
        waitForElement(viewNextMonthButton, 3).click();

        //  Select correct month calendar
        WebElement targetMonth = getElements(titleMonthSelector)
                                    .stream().
                                    filter(month -> month.getText().toLowerCase().contains(monthDeparture))
                                    .findFirst().get().findElement(By.xpath(".//following-sibling::table"));
        if(targetMonth == null){
            throw new Exception("  [Error] - Month "+ monthDeparture+" not found");
        }

        // Calculate return date after a trip of X days duration
        int dayDeparture = world.getDatePlushDays(daysAheadOfDeparture).getDayOfMonth();

        // Select Departure and Return dates
        List<WebElement> days = getElements(targetMonth, By.xpath(".//div[contains(@class,'uitk-day-button')]"));
        days.get(dayDeparture).click();
        days.get(dayDeparture + daysTripDuration).click();

        getElement(buttonDone).click();
    }

    public SearchResult clickSearch(){
        getElement(buttonSubmitSearch).click();
        return new SearchResult(world);
    }


}