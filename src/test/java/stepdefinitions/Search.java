package stepdefinitions;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FooterOptionsPage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.ProductComparisionPage;
import pages.ProductDisplayPage;
import pages.SearchPage;
import stepdefinitions.base.Base;
import utils.CommonUtils;

public class Search extends Base {

	WebDriver driver;
	Properties prop;
	String productLimitOne;

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

	@When("User enters search product with multiple results into the Search box field")
	public void user_enters_search_product_with_multiple_results_into_the_search_box_field() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductThree"));
	}

	@Then("Multiple Product should be displayed in the List view")
	public void multiple_product_should_be_displayed_in_the_list_view() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.getProductCount() > 0);
	}

	@Then("Multiple Product should be displayed in the Grid view")
	public void multiple_product_should_be_displayed_in_the_grid_view() {
		searchPage.selectGridOption();
		Assert.assertTrue(searchPage.getProductCount() > 0);
	}

	@When("User clicks on Product Compare link")
	public void user_clicks_on_product_compare_link() {
		productComparisionPage = searchPage.selectProductCompareOption();
	}

	@Then("User should be navigated to Product Compare page")
	public void user_should_be_navigated_to_product_compare_page() {
		Assert.assertTrue(productComparisionPage.didWeNavigateToProductComparisonPage());
	}

	@Then("Products should get sorted properly on selecting different sorting options")
	public void products_should_get_sorted_properly_on_selecting_different_sorting_options() {
		searchPage.selectSortOptionInDropdownField("Default");
		Assert.assertTrue(searchPage.areProductsDisplayedInAscendingOrder());
	}

	@When("User selects the number of products to be displayed in search results")
	public void user_selects_the_number_of_products_to_be_displayed_in_search_results() {
		productLimitOne = "20";
		searchPage.selectOptionInShowCountDropdown(productLimitOne);
	}

	@Then("Selected number of products should be displayed in search results page")
	public void selected_number_of_products_should_be_displayed_in_search_results_page() {
		Assert.assertTrue(searchPage.getProductCount() <= Integer.parseInt(productLimitOne));
	}

	@When("User navigates to all pages of application")
	public void user_navigates_to_all_pages_of_application() {
		headerOptions = new HeaderOptions(homePage.getDriver());
	}

	@Then("Search box field and search button should be displayed on all pages")
	public void search_box_field_and_search_button_should_be_displayed_on_all_pages() {
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("contactUsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("registerPageURL"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("loginPageURL"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("forgottenPasswordPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		registerPage = headerOptions.navigateToRegisterPage();
		String emailAddress = CommonUtils.generateEmailWithNanoTime();
		accountSuccessPage = registerPage.registeringAnAccount(prop.getProperty("firstname"),
				prop.getProperty("lastname"), emailAddress, prop.getProperty("telephoneNumber"),
				prop.getProperty("validPassword"));
		rightColumnOptions = accountSuccessPage.getRightColumnOptions();
		myAccountPage = rightColumnOptions.clickOnMyAccountOptionAfterLogin();
		myAccountPage.clickOnEditYourAccountInformation();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnChangeYourPasswordOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		addressBookPage = myAccountPage.clickOnModifyYourAddressBoxEntriesOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		addAddressPage = addressBookPage.clickNewAddressButton();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		addressBookPage = addAddressPage.enterMandatoryFieldsAndAddAddress(prop.getProperty("firstname"),
				prop.getProperty("lastname"), prop.getProperty("textInProductDescription"), prop.getProperty("city"),
				prop.getProperty("postCode"));
		editAddressPage = addressBookPage.clickOnEditButton();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		myAccountPage = editAddressPage.clickOnAccountBreadcrumb();
		myAccountPage.clickOnModifyYourWishListOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		searchPage = headerOptions.enterProductAndClickOnSearchButton(prop.getProperty("existingProduct"));
		productDisplayPage = searchPage.clickOnProductOneName();
		shoppingCartPage = productDisplayPage.clickOnAddToCartButtonAndSelectShoppingCartOption();
		checkoutPage = shoppingCartPage.clickOnCheckoutButton();
		checkoutSuccessPage = checkoutPage.placeOrder();
		refreshAndNavigateToPage(checkoutSuccessPage.getDriver(), getBaseURL() + prop.getProperty("myAccountPage"));
		orderHistoryPage = myAccountPage.clickOnViewYourOrderHistoryOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		orderInformationPage = orderHistoryPage.selectViewOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		returnsPage = orderInformationPage.selectReturnOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		returnsPage.selectFirstReasonForReturn();
		returnsPage.clickOnSubmitButton();
		rightColumnOptions = returnsPage.getRightColumnOptions();
		rightColumnOptions.clickOnMyAccountOption();
		myAccountPage.clickOnDownloadsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnRewardPointsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		returnsPage = myAccountPage.clickOnViewYourReturnRequestsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		returnInformationPage = returnsPage.clickOnViewOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		returnInformationPage.clickOnAccountBreadCrumb();
		myAccountPage.clickOnYourTransactionOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnRecurringPaymentsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		affiliatePage = myAccountPage.clickOnEditYourAffiliateInformationOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		myAccountPage = affiliatePage.updateAffiliateAccount(prop.getProperty("firstname"));
		myAccountPage.clickOnCustomAffiliateTrackingCodeOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnSubscribeUnsubscribeToNewsletterOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("aboutUsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("deliveryInformationPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("privacyPolicyPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("termsAndConditionsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("brandsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("siteMapPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("desktopsCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("pcSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("macSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("laptopsAndNotebooksCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("macsSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("windowsSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("componentsCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("mikeAndTrackballsSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("monitorsSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("subSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("specialOffersPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("brandsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("giftCertificatesPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("affiliateLoginPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		searchPage = headerOptions.enterProductAndClickOnSearchButton(prop.getProperty("existingProduct"));
		productDisplayPage = searchPage.clickOnProductOneName();
		shoppingCartPage = productDisplayPage.clickOnAddToCartButtonAndSelectShoppingCartOption();
		guestCheckoutPage = shoppingCartPage.clickOnCheckoutButtonWithoutLogin();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
	}

	@When("User navigates to sitemap page")
	public void user_navigates_to_sitemap_page() {
		footerOptionsPage = new FooterOptionsPage(homePage.getDriver());
		siteMapPage = footerOptionsPage.clickOnSiteMap();
	}

	@When("User clicks on search option")
	public void user_clicks_on_search_option() {
		searchPage = siteMapPage.clickOnSearchOption();
	}

	@Then("User should be navigated to search page")
	public void user_should_be_navigated_to_search_page() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
	}

	@Then("Breadcrumb option on the search page should work correctly")
	public void breadcrumb_option_on_the_search_page_should_work_correctly() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
	}

	@Then("All the operations on the search page should be performed with the keyboard keys")
	public void all_the_operations_on_the_search_page_should_be_performed_with_the_keyboard_keys() {
		actions = clickKeyboradKeyMultipleTimes(headerOptions.getDriver(), Keys.TAB, 21);
		actions = typeTextUsingActions(actions, prop.getProperty("existingProduct"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_DOWN, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.SPACE, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 2);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		searchPage = new SearchPage(headerOptions.getDriver());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(getActions(driver), Keys.TAB, 21);
		actions = typeTextUsingActions(actions, prop.getProperty("textInProductDescription"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 3);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.SPACE, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 26);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		productComparisionPage = new ProductComparisionPage(searchPage.getDriver());
		Assert.assertTrue(productComparisionPage.didWeNavigateToProductComparisonPage());
		navigateBackInBrowser(productComparisionPage.getDriver());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_DOWN, 1);
		Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 30);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_DOWN, 1);
		Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 31);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		productDisplayPage = new ProductDisplayPage(searchPage.getDriver());
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsShoppingCartOptionDisplayedOnTheSuccessMessage());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsWishListOptionDisplayedOnTheSuccessMessage());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsProductComparisonOptionDisplayedOnTheSuccessMessage());
	}

	@Then("Proper page heading, page url and page title should be displayed")
	public void proper_page_heading_page_url_and_page_title_should_be_displayed() {
		Assert.assertEquals(searchPage.getPageHeading(), "Search");
		Assert.assertEquals(getPageURL(searchPage.getDriver()), getBaseURL() + prop.getProperty("searchPage"));
		Assert.assertEquals(getPageTitle(searchPage.getDriver()), "Search");
	}

	@Then("Proper UI should be displayed for the search functionality in search Page")
	public void proper_ui_should_be_displayed_for_the_search_functionality_in_search_page() throws IOException {
		String browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedSearchPageUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxSearchPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxSearchPageUI.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeSearchPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeSearchPageUI.png"));
		}
	}

}
