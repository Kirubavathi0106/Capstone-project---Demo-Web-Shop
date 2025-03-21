package com.demowebshop.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.utils.Base;
import com.demowebshop.utils.ReportsScreenshots;

  public class OrderPlacementPage {
	WebDriver driver;
	ExtentTest test;
	WebDriverWait wait;

	public OrderPlacementPage(WebDriver driver, ExtentTest test) {

		this.driver = driver;
		this.test = test;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
     //Locators
	// Selecting "New Address" from the dropdown
	By addressDropdown = By.id("billing-address-select");

	// Billing Details
	By firstNameField = By.id("BillingNewAddress_FirstName");
	By lastNameField = By.id("BillingNewAddress_LastName");
	By emailField = By.id("BillingNewAddress_Email");
	By countryField = By.id("BillingNewAddress_CountryId");

	By addressField = By.id("BillingNewAddress_Address1");
	By cityField = By.id("BillingNewAddress_City");
	By zipCodeField = By.id("BillingNewAddress_ZipPostalCode");
	By phoneNumberField = By.id("BillingNewAddress_PhoneNumber");
	By billingContinueBtn = By.xpath("(//input[@class='button-1 new-address-next-step-button'])[1]");

	// Shipping Method
	By shippingaddress = By.xpath("(//input[@type='button'])[3]");

	By shippingMethod = By.id("shippingoption_1");

	By shippingContinueBtn = By.xpath("//input[contains(@onclick, 'ShippingMethod.save')]");

	// Payment Method
	By paymentMethodVisa = By.xpath("//input[@value='Payments.Manual']");

	By paymentContinueBtn = By.xpath("//input[@onclick='PaymentMethod.save()']");
	
	By OrderContinue = By.xpath("//input[@class='button-2 order-completed-continue-button']");
	
	By logoutlink = By.linkText("Log out");

	// Payment Details
	// By cardType = By.id("CreditCardType");
	By cardholder = By.xpath("//input[@id='CardholderName']");
	By cardnumber = By.xpath("//input[@id='CardNumber']");
	By expiryMonth = By.xpath("(//select[@id='ExpireMonth'])//option[10]");
	By expiryYear = By.xpath("(//select[@id='ExpireYear'])//option[8]");
	By cvv = By.id("CardCode");
	By paymentInfoContinueBtn = By.xpath("//input[@onclick='PaymentInfo.save()']");

	// Confirm Order
	By confirmOrderBtn = By.xpath("//input[@onclick='ConfirmOrder.save()']");

	By orderConfirmationMessage = By
			.xpath("//div[@class='title']/strong[contains(text(),'Your order has been successfully processed!')]");
	    
	    public boolean isCheckoutPageDisplayed() {
	        boolean actResult = true; 

	        try {
	            if (driver.getTitle().contains("Checkout")) {
	                ReportsScreenshots.generateReport(driver, test, Status.PASS, "Checkout page displayed successfully.");
	            } else {
	                actResult = false;
	                ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Checkout page title does not match.");
	            }
	        } catch (TimeoutException e) {
	        	actResult = false;
	            ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Checkout page NOT displayed due to timeout: " + e.getMessage());
	        }
	        
	        return actResult;
	    }

	public void selectNewAddress() {
		Select select = new Select(driver.findElement(addressDropdown));
		select.selectByVisibleText("New Address");
	}

	public void enterBillingDetails() {
		driver.findElement(firstNameField).clear();
		driver.findElement(firstNameField).sendKeys("Kiruba");
		driver.findElement(lastNameField).clear();
		driver.findElement(lastNameField).sendKeys("Vathi");
//		driver.findElement(emailField).clear();
//		driver.findElement(emailField).sendKeys("Kirubavathi12@gmail.com");
		WebElement countryDropdown = driver.findElement(countryField);
		Select select = new Select(countryDropdown);
		select.selectByValue("41");
		driver.findElement(addressField).sendKeys("123 Street");
		driver.findElement(cityField).sendKeys("Banglore");
		driver.findElement(zipCodeField).sendKeys("450286");
		driver.findElement(phoneNumberField).sendKeys("9876543210");
		wait.until(ExpectedConditions.elementToBeClickable(billingContinueBtn)).click();
	}

	public void selectShippingMethod() {
		wait.until(ExpectedConditions.elementToBeClickable(shippingaddress)).click();
		wait.until(ExpectedConditions.elementToBeClickable(shippingMethod)).click();

		wait.until(ExpectedConditions.elementToBeClickable(shippingContinueBtn)).click();
	}

	public void selectPaymentMethod() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodVisa)).click();
		driver.findElement(paymentContinueBtn).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(cardholder)).sendKeys("Kirubavathi");
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardnumber)).sendKeys("4111111111111111");
		wait.until(ExpectedConditions.visibilityOfElementLocated(expiryMonth));
		wait.until(ExpectedConditions.visibilityOfElementLocated(expiryYear));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cvv)).sendKeys("123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentInfoContinueBtn)).click();
	}

	public boolean confirmOrder() {
		boolean actResult = true;
	    try {
	        WebElement confirmButton = wait.until(ExpectedConditions.presenceOfElementLocated(confirmOrderBtn));
	        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
	        ReportsScreenshots.generateReport(driver, test, Status.PASS, "Confirm Order button clicked successfully.");
	    } catch (Exception e) {
	    	 actResult = false;
	        ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Confirm Order button was not clickable: " + e.getMessage());
	    }
	    return actResult;
	}


	public boolean getOrderConfirmationMessage() {
	    boolean actResult = true;
	    
	    try {
	        WebElement confirmationMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationMessage));
	        String confirmationMsg = confirmationMsgElement.getText();
	        
	        ReportsScreenshots.generateReport(driver, test, Status.PASS, "Order confirmation message displayed: " + confirmationMsg);
	        
	    } catch (Exception e) {
	        actResult = false;
	        ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Order confirmation message was not displayed: " + e.getMessage());
	    }
	    
	    return actResult;
	}

	
	public void continuetohomepage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(OrderContinue)).click();
	}	
		 public boolean isLogoutButtonDisplayed() {
		        boolean actResult = true;
		        try {
		            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutlink)).click();
		            ReportsScreenshots.generateReport(driver, test, Status.PASS, "Logout button displayed and clicked successfully.");
		        } catch (TimeoutException e) {
		            actResult = false;
		            ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Logout button NOT displayed." + e.getMessage());
		        }
		        return actResult;
		    }

	}

