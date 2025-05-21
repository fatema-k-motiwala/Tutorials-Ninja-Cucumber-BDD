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
import pages.HomePage;
import pages.MyAccountPage;
import pages.RightColumnOptions;
import stepdefinitions.base.Base;
import utils.CommonUtils;

public class Login extends Base {

	WebDriver driver;
	Properties prop;
	
	@Given("User navigates to Login page")
	public void user_navigates_to_login_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
		homePage.clickOnmyAccountDropMenu();
		loginPage = homePage.selectLoginOption();
	}

	@Given("User opens Home Page")
	public void user_opens_Home_Page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
	}

	@When("User enters valid email and valid password into the fields")
	public void user_enters_valid_email_and_valid_password_into_the_fields() {
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
	}

	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
		myAccountPage = loginPage.clickOnLoginButton();
	}

	@Then("User should get logged in successfully")
	public void user_should_get_logged_in_successfully() {
		rightColumnOptions = new RightColumnOptions(myAccountPage.getDriver());
		rightColumnOptions.isLogoutOptionDisplayed();
	}

	@And("User should be taken to My Account page")
	public void User_should_be_taken_to_My_Account_page() {
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

	@When("User enters invalid email and invalid password into the fields")
	public void user_enters_invalid_email_and_invalid_password_into_the_fields() {
		loginPage.enterEmailAddress(CommonUtils.generateEmailWithNanoTime());
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
	}

	@Then("User should not get logged in")
	public void user_should_not_get_logged_in() {
		Assert.assertTrue(loginPage.areWeOnLoginPage());
	}

	@Then("User should get a proper warning message")
	public void user_should_get_a_proper_warning_message() {
		Assert.assertEquals(loginPage.getPageLevelWarning(), "Warning: No match for E-Mail Address and/or Password.");

	}

	@When("User enters invalid email and valid password into the fields")
	public void user_enters_invalid_email_and_valid_password_into_the_fields() {
		loginPage.enterEmailAddress(CommonUtils.generateEmailWithNanoTime());
		loginPage.enterPassword(prop.getProperty("validPassword"));
	}

	@When("User enters valid email and invalid password into the fields")
	public void user_enters_valid_email_and_invalid_password_into_the_fields() {
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
	}

	@When("User does not enter email and password into the fields")
	public void user_does_not_enter_email_and_password_into_the_fields() {
		loginPage.enterEmailAddress("");
		loginPage.enterPassword("");
	}

	@Then("Forgot Password option should be available on the page")
	public void forgot_password_option_should_be_available_on_the_page() {
		Assert.assertTrue(loginPage.isForgottenPasswordLinkDisplayed());
	}

	@And("User selects Login option from menu")
	public void user_selects_login_option_from_menu() {
		loginPage = homePage.selectLoginOption();
	}

	@When("User clicks on My Account dropmenu from Home Page")
	public void user_clicks_on_my_account_dropmenu_from_Home_Page() {
		homePage.clickOnmyAccountDropMenu();
	}

	@When("User logs into the Application using keyboard keys")
	public void user_logs_into_the_application_using_keyboard_keys() {
		actions = clickKeyboradKeyMultipleTimes(getActions(driver), Keys.TAB, 23);
		actions = typeTextUsingActions(actions, prop.getProperty("existingEmail"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, prop.getProperty("validPassword"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 2);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		myAccountPage = new MyAccountPage(driver);
	}

	@Then("Email and Password fields should have placeholder text")
	public void email_and_password_fields_should_have_placeholder_text() {
		Assert.assertEquals(loginPage.getEmailFieldPlaceHolderText(), "E-Mail Address");
		Assert.assertEquals(loginPage.getPasswordFieldPlaceHolderText(), "Password");
	}

	@When("User Logins into the Application")
	public void user_logins_into_the_application() {
		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));

	}

	@When("User clicks on browser back option")
	public void user_clicks_on_browser_back_option() {
		navigateBackInBrowser(myAccountPage.getDriver());

	}

	@Then("User should hot logout")
	public void user_should_hot_logout() {
		refreshPage(myAccountPage.getDriver());
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}
	

	@When("User enters invalid email credentials into the fields {int} times")
	public void user_enters_invalid_email_credentials_into_the_fields_times(Integer int1) {
		String invalidEmail = CommonUtils.generateEmailWithNanoTime();
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
	}

	@Then("User should get a warning for exceeding attempts")
	public void user_should_get_a_warning_for_exceeding_attempts() {
		Assert.assertEquals(loginPage.getPageLevelWarning(),
				"Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");
	}


	@Then("Password field should be toggled to hide its visibility")
	public void password_field_should_be_toggled_to_hide_its_visibility() {
		Assert.assertEquals(loginPage.getPasswordDomAttribute("type"), "password");
	}

	@When("User enters text into Password field")
	public void user_enters_text_into_password_field() {
		loginPage.enterPassword(prop.getProperty("validPassword"));
	}
	

	@When("User copies the text from the Password field")
	public void user_copies_the_text_from_the_password_field() {
		loginPage.copyPasswordFromPasswordField();
	}
	
	@Then("Password text should not copyable")
	public void password_text_should_not_copyable() {
		loginPage.pastePasswordIntoEmailField();
		Assert.assertNotEquals(loginPage.getPastedTextFromEmailField(), prop.getProperty("validPassword"));
	}
	

	@When("User enters random text into Password field")
	public void user_enters_random_text_into_password_field() {
		loginPage.enterPassword(prop.getProperty("randomPassword"));
	}

	@Then("Password text should not visible in Page Source")
	public void password_text_should_not_visible_in_page_source() {
		Assert.assertFalse(getPageSourceCode(loginPage.getDriver()).contains(prop.getProperty("randomPassword")));
		loginPage.clickOnLoginButton();
		Assert.assertFalse(getPageSourceCode(loginPage.getDriver()).contains(prop.getProperty("randomPassword")));
	}
	
	@When("User Logs into the Application")
	public void user_logs_into_the_application() {
		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmailTwo"),
				prop.getProperty("validPasswordTwo"));
		System.out.println(prop.getProperty("existingEmailTwo"));
		System.out.println(prop.getProperty("validPasswordTwo"));
		}

	@When("User chagnes the Password")
	public void user_chagnes_the_password() {
		changePasswordPage = myAccountPage.clickOnChangeYourPasswordOption();
		changePasswordPage.enterPasswordInPasswordField(prop.getProperty("validPasswordThree"));
		changePasswordPage.enterPasswordInPasswordConfirmField(prop.getProperty("validPasswordThree"));
		myAccountPage = changePasswordPage.clickOnContinueButton();
		}
	
	@When("User Logout from the Application")
	public void user_logout_from_the_application() {
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
		homePage = accountLogoutPage.clickOnContinueButton();
		headerOptions = homePage.getHeaderOptions();	
	}
	
	@Then("User should not be able to login with old password")
	public void user_should_not_be_able_to_login_with_old_password() {
		loginPage = headerOptions.navigateToLoginPage();
		loginPage.logInToApplication(prop.getProperty("existingEmailTwo"), prop.getProperty("validPasswordTwo"));
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedMessage);
	}
	
	@Then("User should be able to login with new password")
	public void user_should_be_able_to_login_with_new_password() {
		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmailTwo"),
				prop.getProperty("validPasswordThree"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
		swapPasswords(prop);

	}

	@Then("User should be able to navigate to different pages from Login page")
	public void user_should_be_able_to_navigate_to_different_pages_from_login_page() {
		headerOptions = loginPage.getHeaderOptions();
		contactUsPage = headerOptions.selectPhoneIcon();
		Assert.assertTrue(getPageTitle(contactUsPage.getDriver()).equals("Contact Us"));
		navigateBackInBrowser(contactUsPage.getDriver());

		loginPage = headerOptions.selectHeartIcon();
		Assert.assertEquals(getPageTitle(headerOptions.getDriver()), "Account Login");

		loginPage = headerOptions.selectWishList();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		shoppingCartPage = headerOptions.selectshoppingCartHeaderIcon();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectshoppingCartHeaderOption();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectcheckoutIcon();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectcheckoutOption();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		homePage = headerOptions.selectLogo();
		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		searchPage = headerOptions.selectSearchButton();
		Assert.assertEquals(getPageTitle(searchPage.getDriver()), "Search");
		navigateBackInBrowser(searchPage.getDriver());

//		homePage = headerOptions.selectHomeBreadcrumb();
//		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
//		navigateBackInBrowser(homePage.getDriver());

		loginPage = headerOptions.selectAccountBreadcrumb();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = loginPage.selectLoginBreadcrumb();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		registerPage = loginPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");
		navigateBackInBrowser(registerPage.getDriver());

		forgotYourPasswordPage = loginPage.clickOnForgottenPassword();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgotYourPasswordPage.getDriver());

		loginPage.clickOnLoginButton();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		rightColumnOptions = loginPage.getRightColumnOptions();

		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		registerPage = rightColumnOptions.clickOnRegisterOption();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");
		navigateBackInBrowser(registerPage.getDriver());

		forgotYourPasswordPage = rightColumnOptions.clickOnForgotYourPassword();
		Assert.assertEquals(getPageTitle(forgotYourPasswordPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgotYourPasswordPage.getDriver());

		loginPage = rightColumnOptions.clickOnMyAccountOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnAddressBookOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnDownloadsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRecurringPaymentsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRewardsPointsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnReturnsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnTransactionsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnNewsletterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		footerOptionsPage = loginPage.getFooterOptionsPage();

		aboutUsPage = footerOptionsPage.clickOnAboutUs();
		Assert.assertEquals(getPageTitle(aboutUsPage.getDriver()), "About Us");
		navigateBackInBrowser(aboutUsPage.getDriver());

		deliveryInformationPage = footerOptionsPage.clickOnDeliveryInformation();
		Assert.assertEquals(getPageTitle(deliveryInformationPage.getDriver()), "Delivery Information");
		navigateBackInBrowser(deliveryInformationPage.getDriver());

		privacyPolicyPage = footerOptionsPage.clickOnPrivacyPolicy();
		Assert.assertEquals(getPageTitle(privacyPolicyPage.getDriver()), "Privacy Policy");
		navigateBackInBrowser(privacyPolicyPage.getDriver());

		termsConditionsPage = footerOptionsPage.clickOnTermsConditions();
		Assert.assertEquals(getPageTitle(termsConditionsPage.getDriver()), "Terms & Conditions");
		navigateBackInBrowser(termsConditionsPage.getDriver());

		contactUsPage = footerOptionsPage.clickOnContactUs();
		Assert.assertEquals(getPageTitle(contactUsPage.getDriver()), "Contact Us");
		navigateBackInBrowser(contactUsPage.getDriver());

		returnsPage = footerOptionsPage.clickOnReturns();
		Assert.assertEquals(getPageTitle(returnsPage.getDriver()), "Product Returns");
		navigateBackInBrowser(returnsPage.getDriver());

		siteMapPage = footerOptionsPage.clickOnSiteMap();
		Assert.assertEquals(getPageTitle(siteMapPage.getDriver()), "Site Map");
		navigateBackInBrowser(siteMapPage.getDriver());

		brandsPage = footerOptionsPage.clickOnBrands();
		Assert.assertEquals(getPageTitle(brandsPage.getDriver()), "Find Your Favorite Brand");
		navigateBackInBrowser(brandsPage.getDriver());

		giftCertificatePage = footerOptionsPage.clickOnGiftCertificate();
		Assert.assertEquals(getPageTitle(giftCertificatePage.getDriver()), "Purchase a Gift Certificate");
		navigateBackInBrowser(giftCertificatePage.getDriver());

		affiliatePage = footerOptionsPage.clickOnAffiliate();
		Assert.assertEquals(getPageTitle(affiliatePage.getDriver()), "Affiliate Program");
		navigateBackInBrowser(affiliatePage.getDriver());

		specialsPage = footerOptionsPage.clickOnSpecials();
		Assert.assertEquals(getPageTitle(specialsPage.getDriver()), "Special Offers");
		navigateBackInBrowser(specialsPage.getDriver());

		loginPage = footerOptionsPage.clickOnMyAccount();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = footerOptionsPage.clickOnOrderHistory();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = footerOptionsPage.clickOnWishList();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = footerOptionsPage.clickOnNewsletter();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

	}

	@Then("User should be able to navigate to Login page in different waysl")
	public void user_should_be_able_to_navigate_to_login_page_in_different_waysl() {
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		rightColumnOptions = loginPage.getRightColumnOptions();
		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		registerPage = loginPage.clickOnContinueButton();
		loginPage = registerPage.selectLoginPageOption();
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
	}
	

	@Then("ProperPage Breadcrumb URL Title and Heading for login page should be displayed")
	public void proper_page_breadcrumb_url_title_and_heading_for_login_page_should_be_displayed() {
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		Assert.assertEquals(getPageURL(loginPage.getDriver()), getBaseURL()+prop.getProperty("loginPageURL"));
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		Assert.assertEquals(loginPage.getFirstPageHeading(), "New Customer");
		Assert.assertEquals(loginPage.getSecondPageHeading(), "Returning Customer");
	}
	

	@Then("Proper UI for Login page should be displayed")
	public void proper_ui_for_login_page_should_be_displayed() throws IOException {
		String browserName = (prop.getProperty("browserName"));

		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageChromeUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageChromeUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLoginPageChromeUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageFirefoxUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageFirefoxUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLoginPageFirefoxUI.png"));

		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageFirefoxUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageFirefoxUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLoginPageEdgeUI.png"));
		}
	}























}
