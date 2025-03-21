package com.demowebshop.stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.demowebshop.hooks.Hooks;
import com.demowebshop.pages.RegistrationPage;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.utils.Base;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationLoginSteps {

	WebDriver driver;
	ExtentTest test;

	RegistrationPage registrationPage;
	LoginPage loginPage;

	public RegistrationLoginSteps() {
		this.driver = Hooks.driver;
		this.test = Hooks.test;
		this.registrationPage = new RegistrationPage(driver, test);
		this.loginPage = new LoginPage(driver, test);
	}

	@Given("user on homepage of Website")
	public void user_on_homepage_of_website() {
		System.out.println("User is on home page");
	}

	// ============================
	// Valid Registration Steps
	// ============================

	@When("user navigates to Registration page")
	public void user_navigates_to_registration_page() {
		registrationPage.navigateToRegistrationPage();
	}

	@And("user selects gender {string}")
	public void user_selects_gender(String gender) {
		registrationPage.selectGender(gender);
	}

	@And("user enters first name {string}")
	public void user_enters_first_name(String firstName) {
		registrationPage.enterFirstName(firstName);
	}

	@And("user enters last name {string}")
	public void user_enters_last_name(String lastName) {
		registrationPage.enterLastName(lastName);
	}

	@And("user enters email {string}")
	public void user_enters_email(String email) {
		registrationPage.enterEmail(email);
	}

	@And("user enters password {string}")
	public void user_enters_password(String password) {
		registrationPage.enterPassword(password);
	}

	@And("user enters confirm password {string}")
	public void user_enters_confirm_password(String confirmPassword) {
		registrationPage.enterConfirmPassword(confirmPassword);
	}

	@And("user clicks on the register button")
	public void user_clicks_on_the_register_button() {
		registrationPage.clickRegisterButton();
	}

	@Then("user should see a success message")
	public void user_should_see_a_message_your_registration_completed() {
		boolean result = registrationPage.getRegistrationSuccessMessage();
		Assert.assertTrue(result, "Registration success message was not displayed!");
	}

	@And("user clicks on the logout button")
	public void userClicksOnLogoutButton() {
		registrationPage.clickLogout();
	}

	// ===================================
	// Invalid Registration Steps
	// ===================================
	@Then("user should see an error message {string}")
	public void user_should_see_an_error_message(String expectedErrorMessage) {
		boolean result = registrationPage.getErrorMessage();
		Assert.assertTrue(result, "Expected error message was not displayed!");
	}

	// ============================
	// Valid Login Steps
	// ============================
	@When("the user clicks on login link")
	public void the_user_clicks_on_login_link() {
		loginPage.navigateToLoginPage();
	}

	@And("the user enters email {string} and password {string}")
	public void the_user_enters_email_and_password(String email, String password) {
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
	}

	@And("the user clicks on login button")
	public void the_user_clicks_on_login_button() {
		loginPage.clickLoginButton();
	}

	@Then("the welcome message and logout should be displayed on home page")
	public void the_welcome_message_and_logout_should_be_displayed_on_home_page() {
		Assert.assertTrue(loginPage.isWelcomeMessageDisplayed(), "Welcome message is not displayed");
		Assert.assertTrue(loginPage.isLogoutButtonDisplayed(), "Logout button NOT displayed");
	}

	// ===================================
	// Invalid Login Credentials Steps
	// ===================================
	@And("the user enters email {string} and invalid password {string}")
	public void the_user_enters_email_and_invalid_password(String email, String password) {
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
	}

	@And("the user enters an invalid email {string} and password {string}")
	public void the_user_enters_an_invalid_email_and_password(String email, String password) {
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
	}

	@Then("an error message should be displayed saying {string}")
	public void an_error_message_should_be_displayed_saying(String errorMessage) {
		Assert.assertTrue(loginPage.isErrorMessageDisplayed(errorMessage), "Error message was not displayed");
	}

	// ==============================
	// Forgot Password Recovery
	// ==============================

	@When("the user Click on Forgot Password link")
	public void the_user_click_on_forgot_password_link() {
		loginPage.clickForgotPassword();
	}

	@And("the user Enter {string} in the password recovery field")
	public void the_user_enter_in_the_password_recovery_field(String email) {
		loginPage.enterRecoveryEmail(email);
	}

	@And("the user clicks on Recover button")
	public void the_user_click_on_recover_button() {
		loginPage.clickRecoverButton();
	}

	@Then("the user should see a recovery message {string}")
	public void the_user_should_see_a_recovery_message(String recoveryMessage) {
		Assert.assertTrue(loginPage.isRecoveryMessageDisplayed(recoveryMessage), "Recovery message not displayed");
	}
}
