package stepdefinitions;

import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HeaderOptions;
import pages.HomePage;
import stepdefinitions.base.Base;
import utils.CommonUtils;

public class Search extends Base {

	WebDriver driver;
	Properties prop;

	@Given("User is on the Home page")
	public void user_is_on_the_home_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
	}

	@When("User enters existing product into the Search box field")
	public void user_enters_existing_product_into_the_search_box_field() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
	}

	@And("User clicks on Search button")
	public void user_clicks_on_search_button() {
		searchPage = headerOptions.selectSearchButton();
	}

	@Then("Product should be displayed in the search results")
	public void product_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
	}

	@When("User enters non existing product into the Search box field")
	public void user_enters_non_existing_product_into_the_search_box_field() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("nonexistingProduct"));
	}

	@Then("Proper message should be displayed in the search results")
	public void proper_message_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");
	}

	@When("User does not enter any product into the Search box field")
	public void user_does_not_enter_any_product_into_the_search_box_field() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField("");
	}

	@Given("User is logged into the Application")
	public void user_is_logged_into_the_application() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
	}

	@When("User enters search product into the Search box field")
	public void user_enters_search_product_into_the_search_box_field() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductTwo"));

	}

	@Then("Multiple product details should be displayed in the search results")
	public void multiple_product_details_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.getProductCount() > 0);
	}

	@Then("Search box field should have placeholder text")
	public void Search_box_field_should_have_placeholder_text() {
		Assert.assertEquals(headerOptions.getSearchBoxPlaceHolderText(), "Search");
	}

	@Then("Fields on Search results page should have placeholder text")
	public void fields_on_search_results_page_should_have_placeholder_text() {
		Assert.assertEquals(searchPage.getSearchCriteriaBoxPlaceHolderText(), "Keywords");
	}

	@When("User enters existing product into the Search Criteria field on Search results page")
	public void user_enters_existing_product_into_the_search_criteria_field_on_search_results_page() {
		searchPage.enterTextIntoSearchCriteriaBox(prop.getProperty("existingProduct"));
	}

	@When("User clicks on Search button on Search results page")
	public void user_clicks_on_search_button_on_search_results_page() {
		searchPage.clickOnSearchButton();
	}

	@When("User enters product description text into the Search Criteria field on Search results page")
	public void user_enters_product_description_text_into_the_search_criteria_field_on_search_results_page() {
		searchPage.enterTextIntoSearchCriteriaBox(prop.getProperty("textInProductDescription"));
	}

	@When("User selects Search in Product Descriptions field")
	public void user_selects_search_in_product_descriptions_field() {
		searchPage.clickOnSearchlnProductDescriptionField();
	}

	@Then("Product with searched description text should be displayed in the search results")
	public void product_with_searched_description_text_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());
	}

	@When("User enters a Product from a category into the Search criteria field")
	public void user_enters_a_product_from_a_category_into_the_search_criteria_field() {
		searchPage.enterTextIntoSearchCriteriaBox(prop.getProperty("existingProductTwo"));

	}

	@When("User selects correct Product category")
	public void user_selects_correct_product_category() {
		searchPage.selectOptionFromCategoryIdUsingIndex(
				CommonUtils.convertToInteger(prop.getProperty("CorrectcategoryId")));
	}

	@Then("Product from the selected category should be displayed in the search results")
	public void product_from_the_selected_category_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());

	}

	@When("User selects Wrong Product category")
	public void user_selects_wrong_product_category() {
		searchPage.selectOptionFromCategoryIdUsingIndex(
				CommonUtils.convertToInteger(prop.getProperty("WrongcategoryId")));
	}

	@When("User selects Parent category")
	public void user_selects_parent_category() {
		searchPage.selectOptionFromCategoryIdUsingText(prop.getProperty("ParentCategory"));
	}

	@When("User selects a sub category")
	public void user_selects_a_sub_category() {
		searchPage.clickOnSubCategoryIdCheckBox();
	}

	@When("User clicks on List option")
	public void user_clicks_on_list_option() {
		searchPage.selectListOption();
	}

	@Then("Single Product should be displayed in the List view")
	public void single_product_should_be_displayed_in_the_list_view() {
		Assert.assertTrue(searchPage.getProductCount() == 1);
	}

	@Then("All the Product options are working fine")
	public void all_the_product_options_are_working_fine() {
		searchPage.clickOnAddToCartOption();
		String expectedMessage = "Success: You have added " + prop.getProperty("existingProductTwo")
				+ " to your shopping cart!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnAddToWishListOption();
		expectedMessage = "You must login or create an account to save " + prop.getProperty("existingProductTwo")
				+ " to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnCompareThisProductOption();
		expectedMessage = "Success: You have added " + prop.getProperty("existingProductTwo")
				+ " to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());

	}

	@When("User clicks on Grid option")
	public void user_clicks_on_grid_option() {
		searchPage.selectGridOption();

	}

	@Then("Single Product should be displayed in the Grid view")
	public void single_product_should_be_displayed_in_the_grid_view() {
		Assert.assertTrue(searchPage.getProductCount() == 1);
	}

	@Then("All the Product options are working fine in Grid view")
	public void all_the_product_options_are_working_fine_in_grid_view() {
		searchPage.clickOnAddToCartOption();
		String expectedMessage = "Success: You have added " + prop.getProperty("existingProductTwo")
				+ " to your shopping cart!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnAddToWishListOption();
		expectedMessage = "You must login or create an account to save " + prop.getProperty("existingProductTwo")
				+ " to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnCompareThisProductOption();
		expectedMessage = "Success: You have added " + prop.getProperty("existingProductTwo")
				+ " to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
	}

}
