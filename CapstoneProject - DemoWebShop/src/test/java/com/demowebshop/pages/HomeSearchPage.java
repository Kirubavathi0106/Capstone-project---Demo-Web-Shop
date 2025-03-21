package com.demowebshop.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.utils.ReportsScreenshots;

public class HomeSearchPage {
	WebDriver driver;
	ExtentTest test;
	WebDriverWait wait;

	public HomeSearchPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

//locators
	By featuredProducts = By.xpath("//div[@class='product-grid home-page-product-grid']");
	By searchinputBox = By.xpath("(//input[@id='small-searchterms'])[1]");
	// By searchButton = By.xpath("//input[@class='button-1 search-box-button']");
	By searchButton = By.xpath("//input[@type='submit' and @class='button-1 search-box-button' and @value='Search']");

	By searchResults = By.xpath("//div[@class='product-grid']/div[contains(@class, 'item-box')]");
	By noResultsMessage = By.xpath("//strong[contains(text(), 'No products were found')]");
	By SearchKeyword = By.xpath("//input[contains(@class, 'search-text')]");
	By advSearchCheckbox = By.xpath("//input[@id='As']");

	By categoryDropdown = By.xpath("//select[@id='Cid']");

	By minPriceField = By.xpath("//input[@id='Pf' and @type='text']");
	By maxPriceField = By.xpath("//input[@id='Pt' and @type='text']");

	By advSearchButton = By.xpath("//input[@class='button-1 search-button']");

	// Verify if Search Bar is Displayed
	public boolean isSearchBarDisplayed() {
		boolean actResult = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchinputBox)).isDisplayed();

			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Search Bar displayed ");
		} catch (Exception e) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL,
					"Search Bar is not displayed." + e.getMessage());

		}

		return actResult;
	}

	// Verify if featured products is Displayed
	public boolean isFeaturedProductsDisplayed() {
		boolean actResult = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(featuredProducts)).isDisplayed();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Featured products displayed ");
		} catch (Exception te) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL, "No Feature products displayed." + te.getMessage());
		
		}

		return actResult;
	}

	// Search for a Product
	public void search(String text) {
		WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(searchinputBox));
		searchField.sendKeys(text);
	}

	public void clickSearch() {

		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();// .sendKeys(Keys.ENTER);
	}

	public boolean getSearchResults() {
		boolean isResultsDisplayed = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "search results displayed ");
		} catch (Exception te) {
			isResultsDisplayed = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL,
					"No search results displayed." + te.getMessage());
		}

		return isResultsDisplayed;
	}
//verifying the presence of top products in home page
	public boolean isTopProductsDisplayed() {
		boolean actResult = false; // Default to false

		try {
			String pageSource = driver.getPageSource();

			if (pageSource.contains("Top Products")) {
				ReportsScreenshots.generateReport(driver, test, Status.PASS, "Top Products section is displayed.");
				actResult = true;
			} else {
				ReportsScreenshots.generateReport(driver, test, Status.FAIL,
						"Top Products section is NOT present on the homepage.");
			}
		} catch (Exception e) {
			ReportsScreenshots.generateReport(driver, test, Status.FAIL,
					"Error while checking Top Products: " + e.getMessage());
		}

		return actResult;
	}

	// No Results Message if No Products Found in search(Invalid search scenario)

	public boolean getNoResultsMsg() {
		boolean actResult = true;
		try {
			actResult = wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsMessage)).isDisplayed();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "No products were found that matched your criteria");

		} catch (Exception e) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.PASS,
					"No Results message not found" + e.getMessage());

		}
		return actResult;
	}

	// Enable Advanced Search
	public void enableAdvSearch() {
		WebElement advSearch = wait.until(ExpectedConditions.elementToBeClickable(advSearchCheckbox));
		if (!advSearch.isSelected()) {
			advSearch.click();
		}
	}

	// Select Category in Advanced Search
	public void selectCat(String category) {
		WebElement categoryDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
		Select select = new Select(categoryDropdownElement);
		select.selectByVisibleText(category);
	}

	// Filter by price
	public void filterPrice(int min, int max) {
		wait.until(ExpectedConditions.elementToBeClickable(minPriceField)).sendKeys(String.valueOf(min));
		wait.until(ExpectedConditions.elementToBeClickable(maxPriceField)).sendKeys(String.valueOf(max));
	}

	public void ClickAdvSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(advSearchButton)).click();
	}
    //verifying products displayed in search
//	public boolean isProductDisplayedInResult(String product) {
//		try {
//			WebElement res = driver.findElement(searchResults);
//			ReportsScreenshots.generateReport(driver, test, Status.PASS,
//					"The Product displayed in the Result of Advanced Search");
//			return res.getText().contains(product);
//		} catch (NoSuchElementException e) {
//			ReportsScreenshots.generateReport(driver, test, Status.FAIL,
//					"The Product is not displayed in the Result of Advanced Search" + e.getMessage());
//			return false; // No products found
//		}
//	}
	
	public boolean isProductDisplayedInResult(String product) {
	    try {
	        List<WebElement> results = driver.findElements(searchResults);
	        for (WebElement res : results) {
	            if (res.getText().contains(product)) {
	                ReportsScreenshots.generateReport(driver, test, Status.PASS, "Product found in search results: " + product);
	                return true;
	            }
	        }
	        ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Product not found in search results: " + product);
	        return false;
	    } catch (NoSuchElementException e) {
	        ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Error while searching for product: " + e.getMessage());
	        return false;
	    }
	}


	public void ClickProductResult() {
		WebElement res = driver.findElement(searchResults);
		res.click();
		ReportsScreenshots.generateReport(driver, test, Status.PASS, "Navigated to Product Detail Page");

	}

}
