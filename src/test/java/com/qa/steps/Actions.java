package com.qa.steps;

import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.testng.Assert;

import com.qa.util.Browser;
import com.qa.util.DriverFactory;
import com.qa.util.World;
import com.qa.drivers.DriverManager;
import com.qa.pages.Home;
import com.qa.pages.PackagesFlights;
import com.qa.pages.SearchResult;
 
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Actions{


    //DriverManager driverManager;

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

        packagesFlights.writeCityOrAirport(mapData.get(0).get("Origin City"));
        packagesFlights.writeDestination(mapData.get(0).get("Destination City"));
        packagesFlights.writeDepartureDate(mapData.get(0).get("Departing Date"));
        packagesFlights.writeReturnDate(mapData.get(0).get("Returning Date"));
        packagesFlights.writeNumberOfTravelers(mapData.get(0).get("Airline Class"));

        searchResults = packagesFlights.clickSearch();
    }

    @And("^the Property Class filter is selected for (.*) stars$")
    public void setPropertyClassFilter(String stars){
      
        searchResults.selectPropertyClassByStarNumber(Integer.parseInt(stars));
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

    public void goSleep(int secs){
        try {
            Thread.sleep(secs*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}