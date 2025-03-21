package com.demowebshop.stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.demowebshop.hooks.Hooks;
import com.demowebshop.pages.ShoppingCartPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingCartSteps {
	WebDriver driver;
	ExtentTest test;
	ShoppingCartPage shoppingCartPage;

	public ShoppingCartSteps() {
		this.driver = Hooks.driver;
		this.test = Hooks.test;
		this.shoppingCartPage = new ShoppingCartPage(driver, test);

	}

	@Given("the user goes to the shopping cart page")
	public void goToShoppingCart() {
		boolean isClicked = shoppingCartPage.clickCartLink();
		Assert.assertTrue(isClicked, "Cart link was not clicked.");

	}

	@Then("the user updates the quantity of {string} to {string}")
	public void userUpdatesTheQuantity(String productName, String newQuantity) {
		boolean actResult = shoppingCartPage.updateProductQuantity(productName, newQuantity);
		Assert.assertTrue(actResult);
		shoppingCartPage.clickUpdateCart();
	}

	@When("the user removes the {string} from the cart")
	public void removeProductFromCart(String productName) {
		boolean actResult = shoppingCartPage.removeProduct(productName);
		Assert.assertTrue(actResult, "Failed to remove product: " + productName);
	}

	@When("the user clicks the {string} button")
	public void clickButton(String button) {
		if (button.equalsIgnoreCase("Update shopping cart")) {
			shoppingCartPage.clickUpdateCart();
		} else if (button.equalsIgnoreCase("Checkout")) {
			shoppingCartPage.clickCheckout();
		}
	}

	@Then("the cart should reflect the updated items and quantities")
	public void verifyUpdatedCart() {
		boolean isUpdated = shoppingCartPage.verifyCartUpdate("Smartphone", "3");
		Assert.assertTrue(isUpdated);
	}

	@And("user enables the termscheckbox")
	public void enablesterms() {
		shoppingCartPage.acceptTerms();
	}

	@And("the user selects the checkout button")
	public void proceedToCheckout() {
		boolean actResult = shoppingCartPage.clickCheckout();
		Assert.assertTrue(actResult);
	}
}
