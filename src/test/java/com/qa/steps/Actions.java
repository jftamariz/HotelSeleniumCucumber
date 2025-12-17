package com.qa.steps;

import com.qa.pages.Home;
import com.qa.pages.PackagesFlights;
import com.qa.pages.SearchResult;
import com.qa.util.World;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class Actions{

    Home home;
    PackagesFlights packagesFlights;
    SearchResult searchResults;
    private WebDriver driver;
    private World world;

    public Actions(World world){
        this.world = world;
        this.driver = world.driver;
    }

    @Given("^user launches Hotel.com$")
    public void launchBrowser(){
        home = new Home(world);
    }

    @And("^navigate to Packages and Flights$")
    public void navigatePackagesFlights(){
        packagesFlights = home.goPackagesFlights();

    }

    @Then("^the links in the top banner should be displayed$")
    public void verifyHotelFlightsTopBanner(DataTable table){
        List<String> dataLinks = table.asList();

        for(int i = 1; i < dataLinks.size(); i++){
          
            Assert.assertNotNull(packagesFlights.getHeaderMenuItemByName(dataLinks.get(i)));
        }
    }

    @When("^user searches for a Flight and Hotel Packages$")
    public void searchFlightsAndHotels(DataTable table){

        List<Map<String, String>> mapData =  table.asMaps();

        // packagesFlights.writeCityOrAirport(mapData.get(0).get("Origin City"));
        packagesFlights.writeDestination(mapData.get(0).get("Destination City"));
        packagesFlights.writeDepartureandReturnDatesForNextMonth( mapData.get(0).get("Departing Date"), mapData.get(0).get("Returning Date"));
        packagesFlights.writeNumberOfTravelers(mapData.get(0).get("Airline Class"));

        searchResults = packagesFlights.clickSearch();
    }

    @When("^user searches by destination, accommodations or landmarks$")
    public void searchByDestination(DataTable table) throws Exception{
        List<Map<String, String>> mapData =  table.asMaps();
        home.writeGoingToDestination(mapData.get(0).get("Going To"));
        home.writeDestinationDates(42, 5);
        searchResults = home.clickSearch();
    }

    @And("^the Property Class filter is selected for (.*) stars$")
    public void setPropertyClassFilter(String stars){
      
        searchResults.selectPropertyClassByStarNumber(Integer.parseInt(stars));
        home.goSleep(6);
    }

    @And("^the Neighborhood filter is set to (.*)$")
    public void setNeighborhoodFilter(String neighborhood){
        searchResults.selectNeighborhoodByName(neighborhood);
    }

    @Then("^verify the Neighborhood filter was set to (.*)$")
    public void verifyNeighborhoodFilter(String neighborhood){
       
        List<Map<String,String>> rowsHotel = searchResults.readHotelInformationRows();
        Assert.assertTrue(rowsHotel.get(0).get("neighborhood").contains(neighborhood));
    }
}