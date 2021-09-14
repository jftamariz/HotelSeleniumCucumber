package com.qa.steps;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import com.qa.util.World;


public class Hooks {

    private WebDriver driver;
    private World world;


    public Hooks(World world) {
        this.world = world;
        this.driver = world.driver;
    }


    @After
	public void teardown() {
		System.out.println("Close browser");
		//driver.quit();
	}

}