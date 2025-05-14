package stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountSuccessPage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.NewsLetterPage;
import pages.RegisterPage;
import pages.RightColumnOptions;
import stepdefinitions.base.Base;
import utils.CommonUtils;

public class Register extends Base{

	WebDriver driver;
	Properties prop;
	HomePage homepage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	MyAccountPage myAccountPage;
	NewsLetterPage newsLetterPage;
	LoginPage loginPage;
	RightColumnOptions rightColumnOptions;
	Actions actions ;
	HeaderOptions headerOptions;
	
	@Given("User navigates to Register Account Page")
	public void user_navigates_to_register_account_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homepage = new HomePage(driver);
		homepage.clickOnmyAccountDropMenu();
		registerPage = homepage.selectRegisterOption();
	}

	@Given("User opens Application URL in the browser")
	public void user_opens_application_url_in_the_browser() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homepage = new HomePage(driver);
	}

	@When("User clicks on My Account dropmenu")
	public void user_clicks_on_my_account_dropmenu() {
		homepage.clickOnmyAccountDropMenu();
	}

	@When("User selects Register option")
	public void user_selects_register_option() {
		registerPage = homepage.selectRegisterOption();
	}

	@Then("User should be navigated to Register Account page")
	public void user_should_be_navigated_to_register_account_page() {
		Assert.assertTrue(registerPage.didWeNavigatetoRegisterpage());
	}

	@When("User enters below fields")
	public void user_enters_below_fields(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		registerPage.enterFirstname(map.get("firstname"));
		registerPage.enterLastname(map.get("lastname"));
		registerPage.enterEmail(CommonUtils.generateEmailWithNanoTime());
		registerPage.enterTelephone(map.get("telephone"));
		registerPage.enterPassword(map.get("password"));
		registerPage.enterConfirmPassword(map.get("password"));

	}

	@And("User selects Yes option for Newsletter")
	public void User_selects_Yes_option_for_Newsletter() {
		registerPage.selectYesNewsletterOption();
	}

	@And("User selects No option for Newsletter")
	public void User_selects_No_option_for_Newsletter() {
		registerPage.selectNoNewsletterOption();
		;
	}

	@And("User selects Privacy Policy Field")
	public void user_selects_privacy_policy_field() {
		registerPage.selectprivacyPolicy();
	}

	@And("User clicks on Continue button")
	public void user_clicks_on_continue_button() {
		accountSuccessPage = registerPage.clickOnContinueButton();
	}

	@Then("User account should get logged in")
	public void user_account_should_get_logged_in() {
		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
	}

	@And("User should be taken to Account Sucess Page")
	public void user_should_be_taken_to_account_sucess_page() {
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());
	}

	@And("Proper details should be displayed on the Account Sucess Page")
	public void proper_details_should_be_displayed_on_the_account_sucess_page() {
		Assert.assertTrue(accountSuccessPage.getContent().contains("Your Account Has Been Created!"));
		Assert.assertTrue(accountSuccessPage.getContent()
				.contains("Congratulations! Your new account has been successfully created!"));
		Assert.assertTrue(accountSuccessPage.getContent().contains(
				"You can now take advantage of member privileges to enhance your online shopping experience with us."));
		Assert.assertTrue(accountSuccessPage.getContent().contains(
				"If you have ANY questions about the operation of this online shop, please e-mail the store owner."));
		Assert.assertTrue(accountSuccessPage.getContent().contains(
				"A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us."));
	}

	@When("User clicks on Continue button on Account Sucess Page")
	public void user_clicks_on_continue_button_on_account_sucess_page() {
		myAccountPage = accountSuccessPage.clickOnContinueButton();
	}

	@Then("User should be navigated to My Account Page")
	public void user_should_be_navigated_to_my_account_page() {
		Assert.assertEquals("My Account", driver.getTitle());
	}

	@Then("Proper Warning Messages should be displayed")
	public void proper_warning_messages_should_be_displayed() {
		registerPage.clickOnContinueButton();

		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";

		Assert.assertEquals(registerPage.getfirstNameWarning(), expectedFirstNameWarning);
		Assert.assertEquals(registerPage.getlastNameWarning(), expectedLastNameWarning);
		Assert.assertEquals(registerPage.getemailWarning(), expectedEmailWarning);
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		Assert.assertEquals(registerPage.getPageLevelWarning(), expectedPrivacyPolicyWarning);
	}

	@When("User clicks on Subscribe or UnSubscribe to NewsLetter Option")
	public void user_clicks_on_subscribe_or_un_subscribe_to_news_letter_option() {
		newsLetterPage = myAccountPage.clickOnSubscribeUnsubscribeToNewsletterOption();
	}

	@Then("Yes option in the newsletter page should be displayed as selected")
	public void yes_option_in_the_newsletter_page_should_be_displayed_as_selected() {
		Assert.assertTrue(newsLetterPage.didWeNavigateToNewsletterPage());
		Assert.assertTrue(newsLetterPage.isyesNewsletterOptionSelected());
	}

	@Then("No option in the newsletter page should be displayed as selected")
	public void no_option_in_the_newsletter_page_should_be_displayed_as_selected() {
		Assert.assertTrue(newsLetterPage.didWeNavigateToNewsletterPage());
		Assert.assertTrue(newsLetterPage.isNoNewsletterOptionSelected());
	}

	@And("User selects Login option")
	public void user_selects_login_option() {
		loginPage = homepage.selectLoginOption();
	}

	@When("User clicks on Continue button on Login Page")
	public void user_clicks_on_continue_button_on_login_page() {
		registerPage = loginPage.clickOnContinueButton();
	}

	@When("Clicks on Register option from Right column options")
	public void clicks_on_register_option_from_right_column_options() {
		rightColumnOptions = loginPage.getRightColumnOptions();
		registerPage = rightColumnOptions.clickOnRegisterOption();

	}

	@When("User enters the below fields")
	public void user_enters_the_below_fields(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		registerPage.enterFirstname(map.get("firstname"));
		registerPage.enterLastname(map.get("lastname"));
		registerPage.enterEmail(CommonUtils.generateEmailWithNanoTime());
		registerPage.enterTelephone(map.get("telephone"));
		registerPage.enterPassword(map.get("password"));
		registerPage.enterConfirmPassword(map.get("confirmPassword"));
	}

	@Then("Proper warning messages regarding password mistmatch should be displayed")
	public void proper_warning_messages_regarding_password_mistmatch_should_be_displayed() {
		String expectedWarning = "Password confirmation does not match password!";
		Assert.assertEquals(expectedWarning, registerPage.getPasswordConfirmationWarning());
	}

	@When("User enters below fields except email field")
	public void user_enters_below_fields_except_email_field(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		registerPage.enterFirstname(map.get("firstname"));
		registerPage.enterLastname(map.get("lastname"));
		registerPage.enterTelephone(map.get("telephone"));
		registerPage.enterPassword(map.get("password"));
		registerPage.enterConfirmPassword(map.get("password"));
	}

	@When("User enters an existing email address into the email field")
	public void user_enters_an_existing_email_address_into_the_email_field() {
		registerPage.enterEmail(prop.getProperty("existingEmail"));
	}

	@Then("Proper warning messages regarding account exists with this email should be displayed")
	public void proper_warning_messages_regarding_account_exists_with_this_email_should_be_displayed() {
		String expectedWarning = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(expectedWarning, registerPage.getPageLevelWarning());

	}

	@When("User enters an invalid email address into the email field")
	public void user_enters_an_invalid_email_address_into_the_email_field() {
		registerPage.enterEmail(prop.getProperty("invalidEmailOne"));
	}

	@Then("Proper warning message provide valid email address should be displayed")
	public void proper_warning_message_provide_valid_email_address_should_be_displayed() {
		String browserName = (prop.getProperty("browserName"));

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			String expectedWarningMessageOne = "Please include an '@' in the email address. 'amotoori' is missing an '@'.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		}

	}

	@Then("Proper warning message to provide valid phone number should be displayed")
	public void proper_warning_message_to_provide_valid_phone_number_should_be_displayed() {
		String expectedWarningMessage = "Telephone number entered by you is invalid!";
		boolean b = false;
		try {
			if (registerPage.gettelephoneWarning().equals(expectedWarningMessage)) {
				b = true;
			}
		} catch (NoSuchElementException e) {
			b = false;
		}
		Assert.assertTrue(b);

		Assert.assertFalse(accountSuccessPage.didWeNavigateToAccountSuccessPage());

	}

	@When("User fills all the below fields using keyboard keys")
	public void user_fills_all_the_below_fields_using_keyboard_keys(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		actions = clickKeyboradKeyMultipleTimes(getActions(driver), Keys.TAB, 23);
		actions = typeTextUsingActions(actions, map.get("firstname"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, map.get("lastname"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, CommonUtils.generateEmailWithNanoTime());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, map.get("telephone"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, map.get("password"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, map.get("password"));
	
	}
	@When("User selects newsletter and privacy policy field using keyboard keys")
	public void user_selects_newsletter_and_privacy_policy_field_using_keyboard_keys() {
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_LEFT, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 2);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.SPACE, 1);
 
	}
	@When("User selects Continue button also using keyboard")
	public void user_selects_continue_button_also_using_keyboard() {
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		accountSuccessPage = new AccountSuccessPage(driver);
	}
	
	@Then("Proper Placeholder texts should be displayed for all the text fields")
	public void proper_placeholder_texts_should_be_displayed_for_all_the_text_fields() {
		Assert.assertEquals(registerPage.getFirstNamePlaceHolderText(), "First Name");
		Assert.assertEquals(registerPage.getLasttNamePlaceHolderText(), "Last Name");
		Assert.assertEquals(registerPage.getEmailPlaceHolderText(), "E-Mail");
		Assert.assertEquals(registerPage.getTelephonePlaceHolderText(), "Telephone");
		Assert.assertEquals(registerPage.getPasswordDomAttribute("placeholder"), "Password");
		Assert.assertEquals(registerPage.getConfirmPasswordDomAttribute("placeholder"), "Password Confirm");

	}

	@Then("All the mandatory fields in Register Account page should be marked with * symbol")
	public void all_the_mandatory_fields_in_register_account_page_should_be_marked_with_symbol() {
		String expectedContent = "\"* \"";
		String expectedColor = "rgb(255, 0, 0)";

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String fistNameLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getFirstNameLabel());
		Assert.assertEquals(fistNameLabelContent, expectedContent);
		String fistNameLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getFirstNameLabel());
		Assert.assertEquals(fistNameLabelColor, expectedColor);

		String lastNameLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getLastNameLabel());
		Assert.assertEquals(lastNameLabelContent, expectedContent);
		String lastNameLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getLastNameLabel());
		Assert.assertEquals(lastNameLabelColor, expectedColor);

		String emailLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getEmailLabel());
		Assert.assertEquals(emailLabelContent, expectedContent);
		String emailLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getEmailLabel());
		Assert.assertEquals(emailLabelColor, expectedColor);

		String telephoneLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getTelephoneLabel());
		Assert.assertEquals(telephoneLabelContent, expectedContent);
		String telephoneLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getTelephoneLabel());
		Assert.assertEquals(telephoneLabelColor, expectedColor);

		String passwordLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPasswordLabel());
		Assert.assertEquals(passwordLabelContent, expectedContent);
		String passwordLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getPasswordLabel());
		Assert.assertEquals(passwordLabelColor, expectedColor);

		String passwordConfirmLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPasswordConfirmLabel());
		Assert.assertEquals(passwordConfirmLabelContent, expectedContent);
		String passwordConfirmLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getPasswordConfirmLabel());
		Assert.assertEquals(passwordConfirmLabelColor, expectedColor);

		String privacyPolicyLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPrivacyPolicyLabel());
		Assert.assertEquals(privacyPolicyLabelContent, expectedContent);
		String privacyPolicyLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getPrivacyPolicyLabel());
		Assert.assertEquals(privacyPolicyLabelColor, expectedColor);

	}

	@When("User enters only spaces into all the mandatory fields")
	public void user_enters_only_spaces_into_all_the_mandatory_fields() {
		registerPage.enterFirstname("     ");
		registerPage.enterLastname("     ");
		registerPage.enterTelephone("     ");
		registerPage.enterPassword("     ");
		registerPage.enterConfirmPassword("     ");
	}
	
	@Then("Warning message should be displayed for these Mandatory fields")
	public void warning_message_should_be_displayed_for_these_mandatory_fields() {
		String browserName = (prop.getProperty("browserName"));
		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone does not appear to be valid!";

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			Assert.assertEquals(registerPage.getfirstNameWarning(), expectedFirstNameWarning);
			Assert.assertEquals(registerPage.getlastNameWarning(), expectedLastNameWarning);
			Assert.assertEquals(registerPage.getemailWarning(), expectedEmailWarning);
			Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		} else if (browserName.equals("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		}

	}

	@When("User enters below fields except password field")
	public void user_enters_below_fields_except_password_field(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		registerPage.enterFirstname(map.get("firstname"));
		registerPage.enterLastname(map.get("lastname"));
		registerPage.enterEmail(CommonUtils.generateEmailWithNanoTime());
		registerPage.enterTelephone(map.get("telephone"));
	}

	@And("^User enters (.+) which is not following password complexity standards$")
	public void user_enters_which_is_not_following_password_complexity_standards(String password) {
		registerPage.enterPassword(password);
		registerPage.enterConfirmPassword(password);
		System.out.println(password);
	}
	
	@Then("Proper Warning messages should be displayed about password complexity not being followed")
	public void proper_warning_messages_should_be_displayed_about_password_complexity_not_being_followed() {

		String expectedWarning = "Enter password which follows Password Complexity Standard!";
		boolean b = false;
		try {
			if (registerPage.getpasswordWarning().equals(expectedWarning)) {
				b = true;
			}
		} catch (NoSuchElementException e) {
			b = false;
		}
		Assert.assertTrue(b);
	}

	@Then("All the fields in the Register Account page are designed according to the Client Requirements")
	public void all_the_fields_in_the_register_account_page_are_designed_according_to_the_client_requirements() throws IOException {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		// First Name Field check
		Assert.assertEquals(registerPage.getFirstNameCSSValue("height"), expectedHeight);
		;
		Assert.assertEquals(registerPage.getFirstNameCSSValue("width"), expectedWidth);

		String exptectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getfirstNameWarning(), exptectedFirstNameWarning);
		registerPage.enterFirstname("a");
		registerPage.clickOnContinueButton();
		boolean firstNameWarningStatus = false;
		try {
			firstNameWarningStatus = registerPage.isFirstNameWarningDisplayed();
		} catch (NoSuchElementException e) {
			firstNameWarningStatus = false;
		}
		Assert.assertFalse(firstNameWarningStatus);
		registerPage.clearFirstNameField();
		registerPage.enterFirstname("bcdeabcdeabcdeabcdeabcdeabcdeaba");
		registerPage.clickOnContinueButton();
		firstNameWarningStatus = false;
		try {
			firstNameWarningStatus = registerPage.isFirstNameWarningDisplayed();
		} catch (NoSuchElementException e) {
			firstNameWarningStatus = false;
		}
		Assert.assertFalse(firstNameWarningStatus);
		registerPage.clearFirstNameField();
		registerPage.enterFirstname("abcdeabcdeabcdeabcdeabcdeabcdeabc");

		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getfirstNameWarning(), exptectedFirstNameWarning);

		// Last Name Field check
		Assert.assertEquals(registerPage.getLastNameCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getLastNameCSSValue("width"), expectedWidth);

		String exptectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getlastNameWarning(), exptectedLastNameWarning);
		registerPage.enterLastname("a");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		boolean lastNameWarningStatus = false;
		try {
			lastNameWarningStatus = registerPage.isLastNameWarningDisplayed();
		} catch (NoSuchElementException e) {
			lastNameWarningStatus = false;
		}
		Assert.assertFalse(lastNameWarningStatus);
		registerPage.clearlastNameField();
		registerPage.enterLastname("abcdeabcdeabcdeabcdeabcdeabcdeab");
		registerPage.clickOnContinueButton();
		lastNameWarningStatus = false;
		try {
			lastNameWarningStatus = registerPage.isLastNameWarningDisplayed();
		} catch (NoSuchElementException e) {
			lastNameWarningStatus = false;
		}
		Assert.assertFalse(lastNameWarningStatus);
		registerPage.clearEmailField();
		registerPage.enterLastname("abcdeabcdeabcdeabcdeabcdeabcdeabc");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getlastNameWarning(), exptectedLastNameWarning);

		// Email Field check
		Assert.assertEquals(registerPage.getEmailCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getEmailCSSValue("width"), expectedWidth);
		registerPage.enterEmail("adfdsfasdfadfdsssssafasdfasdfasdfadsfasdf@email.com");
		registerPage.clickOnContinueButton();
		boolean emailWarningStatus = false;
		try {
			emailWarningStatus = registerPage.isEmailWarningDisplayed();
		} catch (NoSuchElementException e) {
			emailWarningStatus = false;
		}
		Assert.assertFalse(emailWarningStatus);

		// Telephone Field check
		Assert.assertEquals(registerPage.getTelephoneCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getTelephoneCSSValue("width"), expectedWidth);

		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("1");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("12");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("123");
		registerPage.clickOnContinueButton();
		boolean telephoneWarningStatus = false;
		try {
			telephoneWarningStatus = registerPage.isTelephoneWarningDisplayed();
		} catch (NoSuchElementException e) {
			telephoneWarningStatus = false;
		}
		Assert.assertFalse(telephoneWarningStatus);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("12345678901234567890123456789012");
		registerPage.clickOnContinueButton();
		telephoneWarningStatus = false;
		try {
			telephoneWarningStatus = registerPage.isTelephoneWarningDisplayed();
		} catch (NoSuchElementException e) {
			telephoneWarningStatus = false;
		}
		Assert.assertFalse(telephoneWarningStatus);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("123456789012345678901234567890123");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);

		// Password Field check
		Assert.assertEquals(registerPage.getPasswordCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getPasswordCSSValue("width"), expectedWidth);
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("1");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("12");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("123");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("1234");
		registerPage.clickOnContinueButton();
		boolean passwordWarningStatus = false;
		try {
			passwordWarningStatus = registerPage.isPasswordWarningDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertFalse(passwordWarningStatus);
		registerPage.clearPasswordField();
		registerPage.enterPassword("12345678901234567890");
		registerPage.clickOnContinueButton();
		passwordWarningStatus = false;
		try {
			passwordWarningStatus = registerPage.isPasswordWarningDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertFalse(passwordWarningStatus);
		registerPage.clearPasswordField();
		registerPage.enterPassword("123456789012345678901");
		registerPage.clickOnContinueButton();
		passwordWarningStatus = false;
		try {
			passwordWarningStatus = registerPage.isPasswordWarningDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertTrue(passwordWarningStatus);

		// Password Confirm Field check
		Assert.assertEquals(registerPage.getPasswordConfirmCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getPasswordConfirmCSSValue("width"), expectedWidth);

		// Continue Button
		Assert.assertEquals(registerPage.getContinueButtonCSSValue("color"), "rgba(255, 255, 255, 1)");
		Assert.assertEquals(registerPage.getContinueButtonCSSValue("background-color"), "rgba(34, 154, 200, 1)");
		Assert.assertEquals(registerPage.getContinueButtonCSSValue("font-size"), "12px");

		headerOptions = registerPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		headerOptions.selectRegisterOption();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcScreenshot,
					new File(System.getProperty("user.dir") + "\\Screenshots\\AcutalRAPageAligment.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertFalse(CommonUtils.compareTwoScreenshots(
				System.getProperty("user.dir") + "\\Screenshots\\AcutalRAPageAligment.png",
				System.getProperty("user.dir") + "\\Screenshots\\ExpectedRAPageAligment.png"));


	}










	
	
	
}
