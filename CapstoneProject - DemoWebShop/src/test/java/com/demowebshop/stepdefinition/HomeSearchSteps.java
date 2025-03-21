package com.demowebshop.stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.demowebshop.hooks.Hooks;
import com.demowebshop.pages.HomeSearchPage;
import com.demowebshop.pages.ShoppingCartPage;
import com.demowebshop.utils.Base;
import com.demowebshop.utils.ReportsScreenshots;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeSearchSteps {

	WebDriver driver;
	ExtentTest test;
	HomeSearchPage homePage;

	public HomeSearchSteps() {
		this.driver = Hooks.driver;
		this.test = Hooks.test;
		this.homePage = new HomeSearchPage(driver, test);

	}

	@Given("the user is on the homepage")
	public void the_user_is_on_homepage() {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("demowebshop"), "Not on home page" + currentUrl);
	}

	// verify search bar
	@And("user verifies the search bar")
	public void verify_search_bar() {
		boolean actResult = homePage.isSearchBarDisplayed();
		Assert.assertTrue(actResult);
	}

	// Verifying Featured products
	@Then("user verifies the Featured Products")
	public void verify_featured_products() {
		boolean actResult = homePage.isFeaturedProductsDisplayed();
		Assert.assertTrue(actResult);
	}
   //Top products section verification
	@Then("user verifies the presence of Top Products section")
	public void user_verifies_top_products_section() {
//		boolean actResult = homePage.isTopProductsDisplayed();
//		Assert.assertTrue(actResult);
		boolean result = homePage.isTopProductsDisplayed();
		if (!result) {
			Assert.fail("Bug:'Top Products' section is missing on the homepage. Expected section not found.");
		}

	}

	@When("the user enters {string} in the search box")
	public void search_product(String text) {
		homePage.search(text);
	}

	@And("clicks the search button")
	public void click_search_btn() {
		homePage.clickSearch();

	}

	@Then("the search results for {string} should be displayed")
	public void validate_results_displayed(String productName) {
		boolean actResult = homePage.getSearchResults();
		Assert.assertTrue(actResult);
	}

	// search Functionlity
	@And("the user enables advanced search")
	public void enable_advanced_search() {
		homePage.enableAdvSearch();
	}

	@And("the user selects category {string}")
	public void select_category(String category) {
		homePage.selectCat(category);
	}

	@And("the user sets the price range from {int} to {int}")
	public void set_price_range(int min, int max) {
		homePage.filterPrice(min, max);
	}

	@Then("the search results for {string} should be displayed within the filters and clicked")
	public void the_search_results_for_should_be_displayed_within_the_filters_and_clicked(String product) {
		Assert.assertTrue(homePage.isProductDisplayedInResult(product), "Filtered search results incorrect.");
		homePage.ClickProductResult();

	}

	@When("the user clicks on the search button")
	public void the_user_clicks_on_the_search_button() {
		homePage.ClickAdvSearch();
	}

	// Invalid search scenario
	@When("the user enters {string} in the search bar")
	public void invalid_search(String productName) {
		homePage.search(productName);
		// homePage.clickSearch();
	}

	@Then("a message {string} should be displayed")
	public void validate_no_results_message(String expectedMessage) {
		homePage.ClickAdvSearch();

		boolean isMessageDisplayed = homePage.getNoResultsMsg(); // Check if "No Products Found" message is displayed

		Assert.assertTrue(isMessageDisplayed, "Expected 'No products found' message was not displayed.");
	}

}
