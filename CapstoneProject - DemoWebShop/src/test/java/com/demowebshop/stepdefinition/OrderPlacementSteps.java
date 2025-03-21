package com.demowebshop.stepdefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.demowebshop.hooks.Hooks;
import com.demowebshop.pages.OrderPlacementPage;
import com.demowebshop.pages.ShoppingCartPage;
import com.demowebshop.utils.Base;
import com.demowebshop.utils.ReportsScreenshots;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderPlacementSteps {
	WebDriver driver;
	ExtentTest test;
	OrderPlacementPage checkoutPage;

	public OrderPlacementSteps() {
		this.driver = Hooks.driver;
		this.test = Hooks.test;
		this.checkoutPage = new OrderPlacementPage(driver, test);

	}

	@Given("User is on the Checkout page")
	public void userIsOnCheckoutPage() {
		boolean actResult = checkoutPage.isCheckoutPageDisplayed();
		Assert.assertTrue(actResult, "Checkout page is not displayed!");
	}

	@When("User enters billing details")
	public void userEntersBillingDetails() {
		checkoutPage.selectNewAddress();
		checkoutPage.enterBillingDetails();
	}

	@And("User selects the shipping method")
	public void userSelectsShippingMethod() {
		checkoutPage.selectShippingMethod();
	}

	@And("User chooses the payment method and enters details")
	public void userChoosesPaymentMethod() {
		checkoutPage.selectPaymentMethod();
	}

	@And("User confirms the order")
	public void userConfirmsOrder() {
		boolean actResult = checkoutPage.confirmOrder();
		Assert.assertTrue(actResult, "Order confirmation was not successful!");
	}

	@Then("User should see the order confirmation message")
	public void userShouldSeeOrderConfirmation() {
		boolean actResult = checkoutPage.getOrderConfirmationMessage();
		Assert.assertTrue(actResult, "Order confirmation message is not displayed or incorrect!");
	}

	@And("User should click continue button")
	public void usershouldclickcontinue() {
		checkoutPage.continuetohomepage();
		boolean actResult = checkoutPage.isLogoutButtonDisplayed();
		Assert.assertTrue(actResult, "Logout button is not displayed!");
	}

}
