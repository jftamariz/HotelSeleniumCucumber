package com.qa;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/java/com/qa/features",
        glue = {"com.qa.steps"},
        plugin = {"pretty",
        "json:test-output/cucumber-report.json",
        "html:test-output/cucumber.html",
        "junit:test-output/cucumber.xml"})
public class TestRunner extends AbstractTestNGCucumberTests{




}