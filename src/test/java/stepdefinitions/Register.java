package stepdefinitions;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountSuccessPage;
import pages.HomePage;
import pages.RegisterPage;
import utils.CommonUtils;

public class Register {
	
	WebDriver driver;
	Properties prop;
	HomePage homepage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	@Given("User navigates to Register Account Page")
	public void user_navigates_to_register_account_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homepage = new HomePage(driver);
		homepage.clickOnmyAccountDropMenu();
		registerPage = homepage.selectRegisterOption();
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
		//
	}

	@When("User clicks on Continue button on Account Sucess Page")
	public void user_clicks_on_continue_button_on_account_sucess_page() {
		accountSuccessPage.clickOnContinueButton();
	}

	@Then("User should be navigated to My Account Page")
	public void user_should_be_navigated_to_my_account_page() {
		Assert.assertEquals("My Account", driver.getTitle());
	}

		
	
}
