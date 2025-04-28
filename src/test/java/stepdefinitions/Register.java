package stepdefinitions;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountSuccessPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.NewsLetterPage;
import pages.RegisterPage;
import pages.RightColumnOptions;
import utils.CommonUtils;

public class Register {
	
	WebDriver driver;
	Properties prop;
	HomePage homepage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	MyAccountPage myAccountPage;
	NewsLetterPage newsLetterPage;
	LoginPage loginPage;
	RightColumnOptions rightColumnOptions;
	
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
		registerPage.selectNoNewsletterOption();;
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
		Assert.assertTrue(accountSuccessPage.getContent().contains("Congratulations! Your new account has been successfully created!"));
		Assert.assertTrue(accountSuccessPage.getContent().contains("You can now take advantage of member privileges to enhance your online shopping experience with us."));
		Assert.assertTrue(accountSuccessPage.getContent().contains("If you have ANY questions about the operation of this online shop, please e-mail the store owner."));
		Assert.assertTrue(accountSuccessPage.getContent().contains("A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us."));
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





	
}
