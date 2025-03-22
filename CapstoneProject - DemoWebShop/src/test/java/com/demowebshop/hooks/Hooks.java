package com.demowebshop.hooks;



import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demowebshop.utils.Base;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks { 
	public static WebDriver driver ;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeAll()
	public static void beforeAll() {
		
		try {
			sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/myReport.html");
				
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		 driver = Base.getBrowser();
		 Base.driver=driver;
		 System.out.println("Browser launched for all scenarios.");
	} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@AfterAll()
	public static void afterAll() {
		if (driver != null) {
            //driver.quit();  // Quit browser after all scenarios are executed
            System.out.println("Browser closed after all scenarios.");
		}
		extent.flush();
	}

	@Before()
	public void beforeScenario(Scenario scenario) {
	    test = extent.createTest(scenario.getName()); // Sets the scenario name dynamically
	    System.out.println("Starting Scenario: " + scenario.getName());
	
		//getBrowser(); 
	}

	@After()
	public void afterScenario(){
		 System.out.println("Scenario completed. Keeping browser open for next scenario.");
	
	 // Base.driver.quit();
	}

}
		
	
	


