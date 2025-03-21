package com.demowebshop.utils;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	public static WebDriver driver;

	// public void getBrowser()
	public static WebDriver getBrowser() {

		if (driver == null) {
			Properties prop = PropertyReader.readProperty();
			switch (prop.getProperty("Browser")) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "safari":
				driver = new SafariDriver();
				break;

			default:
				System.out.println("Unknown browser found");
				//return null;
			}

			driver.get(prop.getProperty("URL"));

//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.manage().window().maximize();

		}
		return driver;
	}
}
