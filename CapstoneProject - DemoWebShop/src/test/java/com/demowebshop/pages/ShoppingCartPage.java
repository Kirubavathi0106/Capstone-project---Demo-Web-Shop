package com.demowebshop.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.utils.ReportsScreenshots;

public class ShoppingCartPage {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	public ShoppingCartPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.test = test;
	}

	// Locators
	By quantityField(String productName) {
		return By.xpath("//td/a[contains(text(),'" + productName + "')]/../../td/input[contains(@class,'qty')]");
	}

	By removeCheckbox(String productName) {
		return By.xpath("//td/a[contains(text(),'" + productName + "')]/../../td/input[@type='checkbox']");
	}

	By updateCartButton = By.xpath("//input[@value='Update shopping cart']");
	By checkoutButton = By.xpath("//button[contains(text(),'Checkout')]");
	By cartLink = By.xpath("//span[text()='Shopping cart']");
	By termsCheckbox = By.xpath("//input[@id='termsofservice']");
	By cartQuantityField = By.xpath("//input[contains(@class, 'qty-input')]");
	By cartProductName = By.xpath("//a[contains(@class, 'product-name')]");

	// Actions
	public boolean clickCartLink() {
		boolean isClicked = false;
		try {
			driver.findElement(cartLink).click();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Cart link clicked successfully.");
			isClicked = true; // false if click is not successful
		} catch (Exception e) {
			ReportsScreenshots.generateReport(driver, test, Status.FAIL,
					"Failed to click on Cart link. " + e.getMessage());
		}
		return isClicked;
	}

	public boolean updateProductQuantity(String productName, String quantity) {
		boolean actResult = true;
		try {
			WebElement qtyField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityField(productName)));
			qtyField.clear();
			qtyField.sendKeys(quantity);
			ReportsScreenshots.generateReport(driver, test, Status.PASS,
					"Updated quantity of " + productName + " to " + quantity);
		} catch (Exception e) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL,
					"Failed to update quantity of " + productName + ". Error: " + e.getMessage());
		}
		return actResult;
	}

	public boolean removeProduct(String productName) {
		boolean actResult = true;
		try {
			WebElement removeCheck = wait.until(ExpectedConditions.elementToBeClickable(removeCheckbox(productName)));
			removeCheck.click();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Clicked remove checkbox for: " + productName);
		} catch (Exception e) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL,
					"Remove checkbox not found for: " + productName + ". Error: " + e.getMessage());
		}
		return actResult;
	}

	public void clickUpdateCart() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(updateCartButton)).click();
	}

	public boolean verifyCartUpdate(String expectedProductName, String expectedQuantity) {
		boolean isUpdated = false;
		try {

			WebElement productElement = driver.findElement(cartProductName);
			String actualProductName = productElement.getText().trim();

			// Locate the quantity field using predefined locator
			WebElement quantityElement = driver.findElement(cartQuantityField);

			String actualQuantity = quantityElement.getAttribute("value").trim();

			if (actualProductName.equals(expectedProductName) && actualQuantity.equals(expectedQuantity)) {
				isUpdated = true; // Cart update verified
			}
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "verified cart update");
		} catch (NoSuchElementException e) {
			isUpdated = false; // Product not found or incorrect quantity
			ReportsScreenshots.generateReport(driver, test, Status.FAIL,
					"cart update is not verified" + e.getMessage());
		}
		return isUpdated;
	}

	public void acceptTerms() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(termsCheckbox)).click();

	}

	public boolean clickCheckout() {
		boolean actResult = true;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton)).click();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Successfully clicked the 'Checkout' button.");
		} catch (Exception e) {
			actResult = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL,
					"Bug: Failed to click the 'Checkout' button. Exception: " + e.getMessage());
		}
		return actResult;
	}

}
