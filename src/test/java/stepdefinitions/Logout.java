package stepdefinitions;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import stepdefinitions.base.Base;
import utils.CommonUtils;

public class Logout extends Base {

	WebDriver driver;
	Properties prop;

	@Given("User is logged in")
	public void user_is_logged_in() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
		homePage.clickOnmyAccountDropMenu();
		loginPage = homePage.selectLoginOption();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
	}

	@Given("User opens the application URL in the browser")
	public void user_opens_the_application_url_in_the_browser() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
	}

	@When("User clicks on My Account drop menu")
	public void user_clicks_on_my_account_drop_menu() {
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
	}

	@When("User clicks on Logout option")
	public void user_clicks_on_logout_option() {
		accountLogoutPage = headerOptions.selectLogoutOption();
	}

	@Then("User should get logged out")
	public void user_should_get_logged_out() {
		Assert.assertTrue(accountLogoutPage.didWeNavigateToLogoutPage());
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionDisplayed());
		accountLogoutPage = headerOptions.getLogoutPage();
		accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

	@When("User clicks on Logout option from Right Column options")
	public void User_clicks_on_Logout_option_from_Right_Column_options() {
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
	}

	@When("User browses back in the browser")
	public void user_browses_back_in_the_browser() {
		navigateBackInBrowser(driver);
		refreshPage(driver);
	}

	@When("User clicks on My Account drop menu from home page")
	public void user_clicks_on_my_account_drop_menu_from_home_page() {
		headerOptions = homePage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
	}

	@Then("User should not get loggedin")
	public void user_should_not_get_loggedin() {
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionDisplayed());
	}

	@Then("Logout option should not be displayed under the dropmenu")
	public void logout_option_should_not_be_displayed_under_the_dropmenu() {
		Assert.assertFalse(headerOptions.isLogoutOptionDisplayed());
	}

	@Then("Logout option should not be displayed under Right column")
	public void logout_option_should_not_be_displayed_under_right_column() {
		registerPage = headerOptions.selectRegisterOption();
		rightColumnOptions = registerPage.getRightColumnOptions();
		Assert.assertFalse(rightColumnOptions.isLogoutOptionDisplayed());
	}
	
	@When("User logsin again")
	public void user_logsin_again() {
		headerOptions = accountLogoutPage.getHeaderOptions();
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
	}
	
	@Then("User should be able to login successfully")
	public void user_should_be_able_to_login_successfully() {
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}


	@Then("Proper Heading, Title, URL and Breadcrumb should be displayed for Account Logout Page")
	public void proper_heading_title_url_and_breadcrumb_should_be_displayed_for_account_logout_page() {
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Account Logout");
		Assert.assertEquals(getPageURL(accountLogoutPage.getDriver()), getBaseURL()+prop.getProperty("logoutPageURL"));
		Assert.assertTrue(accountLogoutPage.didWeNavigateToLogoutPage());
		Assert.assertEquals(accountLogoutPage.getPageHeading(), "Account Logout");
	}


	@Then("Proper UI for Logout option should be displayed")
	public void proper_ui_for_logout_option_should_be_displayed() throws IOException {
 		String browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLogoutOptions.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLogoutOptions.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLogoutOptions.png"));
		}

	}







			
}
