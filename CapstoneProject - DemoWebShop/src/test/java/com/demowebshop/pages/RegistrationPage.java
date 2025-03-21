package com.demowebshop.pages;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.demowebshop.utils.Base;
import com.demowebshop.utils.ReportsScreenshots;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class RegistrationPage { // extends Base {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	public RegistrationPage(WebDriver driver, ExtentTest test) {

		this.driver = driver;
		this.test = test;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	By registerLink = By.linkText("Register");
	By maleRadio = By.id("gender-male");
	By femaleRadio = By.id("gender-female");

	// Personal Details
	By firstName = By.id("FirstName");
	By lastName = By.id("LastName");
	By email = By.id("Email");

	// Password
	By password = By.id("Password");
	By confirmPassword = By.id("ConfirmPassword");

	// Register Button
	By registerButton = By.id("register-button");
	By continueButton = By.xpath("//input[@class='button-1 register-continue-button']");
	By successMessage = By.xpath("//div[@class='result']"); 
	By errorMessage = By.className("field-validation-error");
	By logoutlink = By.linkText("Log out");

	// Method to navigate to Registration page
	public void navigateToRegistrationPage() {
		driver.findElement(registerLink).click();
	}

	// Gender selection
	public void selectGender(String gender) {
		if (gender.equals("Male")) {
			driver.findElement(maleRadio).click();
		} else if (gender.equals("Female")) {
			driver.findElement(femaleRadio).click();
		}

	}

	// Enter first name
	public void enterFirstName(String fname) {
		driver.findElement(firstName).sendKeys(fname);
	}

	// Enter last name
	public void enterLastName(String lname) {
		driver.findElement(lastName).sendKeys(lname);
	}

	// Enter email
	public void enterEmail(String userEmail) {
		driver.findElement(email).sendKeys(userEmail);
	}

	// Enter password
	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}

	// Enter confirm password
	public void enterConfirmPassword(String confirmpassword) {
		driver.findElement(confirmPassword).sendKeys(confirmpassword);
	}

	public void clickRegisterButton() {
		driver.findElement(registerButton).click();
	}

	public boolean getRegistrationSuccessMessage() {
		boolean isSuccessDisplayed = true;

		try {
			String message = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Registration Success Message" + message);
		} catch (Exception e) {
			isSuccessDisplayed = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL, "Registration Success Message Not Displayed.");
			
		}

		return isSuccessDisplayed;
	}

	public void clickContinueButton() {
		driver.findElement(continueButton).click();
	}

	public void clickLogout() {
		driver.findElement(logoutlink).click();
	}

	public boolean getErrorMessage() {
		boolean isErrorDisplayed = false;

		try {
			String errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
			ReportsScreenshots.generateReport(driver, test, Status.PASS, "Error message displayed in Registration page: " + errorText);
			isErrorDisplayed = true;
		} catch (Exception e) {
			isErrorDisplayed = false;
			ReportsScreenshots.generateReport(driver, test, Status.FAIL, "No error message displayed in Registration page." + e.getMessage());
			
		}

		return isErrorDisplayed;
	}

}
