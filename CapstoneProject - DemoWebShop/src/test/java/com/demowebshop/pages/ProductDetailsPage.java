package com.demowebshop.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.utils.Base;
import com.demowebshop.utils.ReportsScreenshots;

public class ProductDetailsPage {
	WebDriver driver;
	ExtentTest test;
	WebDriverWait wait;

	public ProductDetailsPage(WebDriver driver, ExtentTest test) {

		this.driver = driver;
		this.test = test;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	// Locators for product details
	By productTitle = By.xpath("//h1[@itemprop='name']");
	By productShortDescription = By.xpath("//div[@class='short-description']");
	By productFullDescription = By.xpath("//div[@class='full-description']");
	By productPrice = By.xpath("//span[@class='price-value-43']");
	By productimage = By.xpath("//img[@id='main-product-img-43']");

	By productAvailability = By.xpath("//div[@class='stock']");

	// Locators for Add to Wishlist and Add to Cart buttons
	By addToWishlistButton = By.xpath("//input[@id='add-to-wishlist-button-43']");
	// By addToCartButton = By.xpath("//input[@value='Add to cart']");
	By addToCartButton = By.xpath("(//input[@value='Add to cart'])[1]");
	By shoppingcartlink = By.xpath("//span[text()='Shopping cart']");
	By Wishlistlink = By.xpath("//a[contains(@href, '/wishlist')]");
	By wishlistProduct = By.linkText("Smartphone");
	By Verifyaddtocart = By.xpath("//div[@id='bar-notification' and contains(@class, 'success')]");

	// Methods to interact with elements

	public boolean getProductTitle() {
		boolean actResult = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).isDisplayed();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Product title displayed ");
			
		} catch (Exception te) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL, "No Product title displayed.");
		}

		return actResult;
	}

	public boolean getProductDescription() {
		boolean actResult = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(productFullDescription)).isDisplayed();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Product description displayed ");
			
		} catch (Exception te) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL, "No Product description displayed.");
			
		}

		return actResult;
	}

	public boolean getProductPrice() {
		boolean actResult = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).isDisplayed();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Product price displayed ");
			
		} catch (Exception te) {
			ReportsScreenshots.generateReport(driver, test, Status.FAIL, "No Product price displayed.");
			actResult = false;
		}

		return actResult;
	}

	public boolean isProductImageDisplayed() {
		boolean actResult = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(productimage)).isDisplayed();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Product image displayed ");
			actResult = true;
		} catch (Exception te) {
			ReportsScreenshots.generateReport(driver, test, Status.FAIL, "No Product image displayed.");
			actResult = false;
		}

		return actResult;
	}

	public boolean getProductAvailability() {
		boolean actResult = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(productAvailability)).isDisplayed();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Product availability is displayed ");
			
		} catch (Exception e) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Product availability not found: " + e.getMessage());
			
		}

		return actResult;
	}
	public void AddtoCart() {
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
	}
	
	public boolean VerifyAddtoCart() {
	    boolean actResult = true;
	    try {
	        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(Verifyaddtocart));
	        actResult = successMessage.isDisplayed();
	        ReportsScreenshots.generateReport(driver, test, Status.PASS, "Product added to cart successfully.");
	    } catch (Exception e) {
	        actResult = false;
	        ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Failed to verify product addition to cart: " + e.getMessage());
	    }
	    return actResult;
	}

	public void AddToWishlist() {
		driver.findElement(addToWishlistButton).click();
	}

	public void CheckWishlist() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Wishlistlink)).click();
	}

	public boolean VerifyWishlistUpdate() {
		boolean actResult = true;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistProduct));
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Product is present in the wishlist.");
		} catch (TimeoutException e) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Product is NOT present in the wishlist.");
		}
		return actResult;
	}

}