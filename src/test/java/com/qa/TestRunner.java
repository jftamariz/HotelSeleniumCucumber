package com.qa;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/java/com/qa/features",
        glue = {"com.qa.steps"},
        plugin = {"pretty",
        "json:test-output/cucumber-report.json",
        "html:test-output/cucumber.html",
        "junit:test-output/cucumber.xml"})
public class TestRunner extends AbstractTestNGCucumberTests{




}