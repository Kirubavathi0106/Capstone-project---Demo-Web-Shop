package com.demowebshop.stepdefinition;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.demowebshop.hooks.Hooks;
import com.demowebshop.pages.ProductDetailsPage;
import com.demowebshop.utils.Base;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductDetailsSteps extends Base {

	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver, test);

	@Given("the user on product details page")
	public void theUserIsOnProductDetailsPage() {
		System.out.println("Product details page");
	}

	@When("the product title is displayed")
	public void theProductTitleisDisplayed() {
		boolean actResult = productDetailsPage.getProductTitle();
		Assert.assertTrue(actResult);

	}

	@And("the product description should be displayed")
	public void theProductDescriptionShouldBeDisplayed() {
		boolean actResult = productDetailsPage.getProductDescription();
		Assert.assertTrue(actResult);
	}

	@And("the product price should be displayed")
	public void theProductPriceShouldBeDisplayed() {
		boolean actResult = productDetailsPage.getProductPrice();
		Assert.assertTrue(actResult);
	}

	@And("the product image should be displayed")
	public void theproductimageshouldbedisplayed() {
		boolean actResult = productDetailsPage.isProductImageDisplayed();
		Assert.assertTrue(actResult);
	}

	@And("the product availability should be displayed")
	public void theProductAvailabilityShouldBeDisplayed() {
		boolean actResult = productDetailsPage.getProductAvailability();
		Assert.assertTrue(actResult);
	}

	@And("user click on Add to Wishlist button")
	public void iClickOnAddToWishlistButton() {
		productDetailsPage.AddToWishlist();
	}

	@And("user goes to wishlist page")
	public void usergoestowishlistpage() {
		productDetailsPage.CheckWishlist();
	}

	@Then("the user verifies that the product is present in the wishlist")
	public void theProductShouldBePresentInTheWishlist() {
		boolean actResult = productDetailsPage.VerifyWishlistUpdate();
		Assert.assertTrue(actResult);
	}

	@And("user clicks on Add to Cart button")
	public void user_click_on_add_to_cart_button() {
		productDetailsPage.AddtoCart();
		
	}
	
	@Then("user verifies that the product is successfully added to the cart")
	public void userverifiesproductaddedtocart() {
		boolean actResult = productDetailsPage.VerifyAddtoCart();
		Assert.assertTrue(actResult);
	}
	
	
}
