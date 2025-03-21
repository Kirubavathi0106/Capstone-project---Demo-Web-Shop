package com.demowebshop.runner;

	import io.cucumber.testng.AbstractTestNGCucumberTests;
	import io.cucumber.testng.CucumberOptions;

	@CucumberOptions(
	        features = {
	            "src/test/resources/features/DwsHomeSearchProduct.feature",
	            "src/test/resources/features/DwsOrderPlacement.feature",
	            "src/test/resources/features/DwsTestingInValidScenario.feature"
	        },
	        glue = {
	            "com.demowebshop.stepdefinition", 
	            "com.demowebshop.hooks"
	        },
	        plugin = {
	            "pretty",
	            "html:reports/CucumberReport.html",
	            "json:reports/json-report.json",
	            "junit:reports/junit_report.xml"
	        }
	)
	public class DemowebshopRunnerTestCase extends AbstractTestNGCucumberTests {
	}


