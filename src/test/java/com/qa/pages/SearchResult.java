package com.qa.pages;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import com.qa.util.World;


public class SearchResult extends PageBase{

    private By regionResultList = By.id("resultsContainer");
    private By overlaySpinner = By.xpath("//div[@class='opi-overlay']");   //  while updating results

    public SearchResult(World world){
        super(world);
        waitForElement(regionResultList, 15);
    }


    public List<WebElement> getSearchResultList(){
        return getElements(By.xpath("//div[@id='resultsContainer']/section/article"));
    }

    public ChooseRoom selectRowHotelByIndex(int row){
        getSearchResultList().get(row).click();
        return new ChooseRoom(world);
    }

    public List<Map<String,String>> readHotelInformationRows(){
        
        List<Map<String, String>> hotels = new ArrayList<>();
        Map<String,String> hotelInfo;

        List<WebElement> rows = getSearchResultList();
        System.out.println("\n\n total Hotel rows: "+ rows.size());

        if(rows.size()==0){
            System.out.println(" - Zero search results");
            return null;
        }
    
        goSleep(1);
        String discountedPrice = "";
        String actualPrice = "";
        String neighborhood = "";
        WebElement roomPrice;
        WebElement isRoomDiscount;
        for(int i = 0;  i < rows.size(); i++){
            roomPrice = getElement(By.xpath("//article["+(i+1)+"]//ul[@class='hotel-price']//li[contains(@class, 'actualPrice')]"));
            if(roomPrice != null){
                actualPrice = getElement(By.xpath("//article["+(i+1)+"]//ul[@class='hotel-price']//li[contains(@class, 'actualPrice')]")).getText().replaceAll("[\\$,]","").split("\n")[0];
            }else{
                actualPrice = "";
            }
            
            isRoomDiscount = getElement(By.xpath("//article["+(i+1)+"]//ul[@class='hotel-price']//li[contains(@class, 'price ')]"));
            if(isRoomDiscount != null){
                discountedPrice = getElement(By.xpath("//article["+(i+1)+"]//ul[@class='hotel-price']//li[contains(@class, 'price ')]")).getText().replaceAll("[\\$,]","").split("\n")[0];
                //System.out.println(Integer.toString(i)+"  -  "+ getElement(rows.get(i), By.xpath("h3")).getText()+" - Discounted: " +discountedPrice+" - "+actualPrice);
            }else{
                discountedPrice = "";
                //System.out.println(Integer.toString(i)+"  -  "+ getElement(rows.get(i), By.xpath("h3")).getText()+" -  Not Discounted - "+actualPrice);
            }
            neighborhood = getElement(By.xpath("//article["+(i+1)+"]//li[@class='neighborhood secondary ']")).getText();
       
            hotelInfo =  new HashMap<>();
            hotelInfo.put("name", getElement(rows.get(i), By.xpath("//h4[@data-automation='hotel-name']")).getText());
            hotelInfo.put("actualPrice", actualPrice);
            hotelInfo.put("actualPrice", discountedPrice);
            hotelInfo.put("neighborhood", neighborhood);
            hotels.add(hotelInfo);
            
            
        }
        return hotels;
    }


 
    public void selectSortyBy(String sortBy){
        List<WebElement> sortBarItems = getElements(By.xpath("//ul[@class='sort-options nobullet']/li[@class='option']/button"));
 
        sortBarItems.stream()
            .filter(item -> item.getText().equalsIgnoreCase(sortBy))
            .collect(Collectors.toSet())
            .stream()
            .findFirst()
            .get()
            .click();

            waitForElementDisappear(overlaySpinner, 8);
    }

    public void selectPropertyClassByStarNumber(int stars){
        String locatorStars = "star"+Integer.toString(stars);
        waitForElement(By.id(locatorStars)).click();
       

        long startTime = System.currentTimeMillis();
        waitForElement(overlaySpinner, 2);
        waitForElementDisappear(overlaySpinner , 9);
        long endTime = System.currentTimeMillis();
        System.out.println("Wait for Overlay to Disappear: " + (endTime - startTime) + " milliseconds\n");
        
    }

    public void selectNeighborhoodByName(String neighborhood){
   
        WebElement region = getElement(By.id("neighborhood"));

        List<WebElement> neighborhoodItems = getElements(region, By.className("neighborhoodTextLabel"));
  
        neighborhoodItems.stream()
            .filter(item -> item.getText().equalsIgnoreCase(neighborhood))
            .collect(Collectors.toSet())
            .stream()
            .findFirst()
            .get()
            .click();
        waitForElement(overlaySpinner, 3);
        waitForElementDisappear(overlaySpinner, 15);

    }
}