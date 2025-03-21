package com.demowebshop.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.utils.ReportsScreenshots;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	public LoginPage(WebDriver driver, ExtentTest test) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.test = test;
	}

	// Locators
	By loginLink = By.linkText("Log in");
	By emailField = By.id("Email");
	//By emailField = By.xpath("//div[4]/div[1]/div[4]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/form[1]/div[2]/input[1]"); 
	By passwordField = By.id("Password");
	By loginButton = By.xpath("//input[@value='Log in']");
	By errorMessage = By.xpath("//div[@class='message-error']");
	By welcomeMessage = By.className("account");
	By logoutlink = By.linkText("Log out");

	
	// Forgot Password Locators
    By forgotPasswordLink = By.linkText("Forgot password?");
    By recoveryEmailField = By.id("Email");
    By recoverButton = By.xpath("//input[@value='Recover']");
    By recoveryMessage = By.className("result");
    
	// Navigate to Login Page
	public boolean navigateToLoginPage() {
		driver.findElement(loginLink).click();
		return false;
	}

	// Enter Email
	public void enterEmail(String email) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
		driver.findElement(emailField).clear();
		driver.findElement(emailField).sendKeys(email);
	}

	// Enter Password
	public void enterPassword(String password) {
		driver.findElement(passwordField).clear();
		driver.findElement(passwordField).sendKeys(password);
	}

	// Click Login Button
	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}

	// Verify Welcome Message
	public boolean isWelcomeMessageDisplayed() {
        boolean actResult = true;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
            ReportsScreenshots.generateReport(driver, test, Status.PASS, "Login successful. Welcome message displayed.");
        } catch (TimeoutException e) {
            actResult = false;
            ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Welcome message NOT displayed.");
        }
        return actResult;
    }

	// Method to check if logout button is displayed
    public boolean isLogoutButtonDisplayed() {
        boolean actResult = true;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutlink));
            ReportsScreenshots.generateReport(driver, test, Status.PASS, "Logout button displayed successfully.");
        } catch (TimeoutException e) {
            actResult = false;
            ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Logout button NOT displayed.");
        }
        return actResult;
    }

	// Verify Error Message
	 public boolean isErrorMessageDisplayed(String expectedMessage) {
	        boolean actResult = true;
	        try {
	            String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
	            ReportsScreenshots.generateReport(driver, test, Status.PASS, "Error message displayed: " + actualMessage);
	            actResult = actualMessage.contains(expectedMessage);
	        } catch (TimeoutException e) {
	            actResult = false;
	            ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Error message NOT displayed.");
	        }
	        return actResult;
	    }
	
	// Click Forgot Password link
    public void clickForgotPassword() {
        driver.findElement(forgotPasswordLink).click();
    }

 //password recovery
    public void enterRecoveryEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(recoveryEmailField)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(recoveryEmailField)).sendKeys(email);
    }


    public void clickRecoverButton() {
        driver.findElement(recoverButton).click();
    }

    // Verify Recovery Message
    public boolean isRecoveryMessageDisplayed(String expectedMessage) {
        boolean actResult = true;
        try {
            String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(recoveryMessage)).getText();
            ReportsScreenshots.generateReport(driver, test, Status.PASS, "Recovery message displayed: " + actualMessage);
            actResult = actualMessage.contains(expectedMessage);
        } catch (TimeoutException e) {
            actResult = false;
            ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Recovery message NOT displayed." + e.getMessage());
        }
        return actResult;
    }
}

