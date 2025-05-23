package stepdefinitions.base;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import pages.AboutUsPage;
import pages.AccountLogoutPage;
import pages.AccountSuccessPage;
import pages.AffiliatePage;
import pages.BrandsPage;
import pages.ChangePasswordPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.FooterOptionsPage;
import pages.ForgotYourPasswordPage;
import pages.GiftCertificatePage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountInformationPage;
import pages.MyAccountPage;
import pages.NewsLetterPage;
import pages.PrivacyPolicyPage;
import pages.RegisterPage;
import pages.ReturnsPage;
import pages.RightColumnOptions;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.SiteMapPage;
import pages.SpecialsPage;
import pages.TermsConditionsPage;
import utils.CommonUtils;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public HomePage homePage;
	public RegisterPage registerPage;
	public AccountSuccessPage accountSuccessPage;
	public MyAccountPage myAccountPage;
	public NewsLetterPage newsLetterPage;
	public LoginPage loginPage;
	public RightColumnOptions rightColumnOptions;
	public Actions actions;
	public HeaderOptions headerOptions;
	public ContactUsPage contactUsPage; 
	public MyAccountInformationPage myAccountInformationPage;
	public ShoppingCartPage shoppingCartPage;
	public SearchPage searchPage;
	public ForgotYourPasswordPage forgotYourPasswordPage;
	public FooterOptionsPage footerOptionsPage;
	public AboutUsPage aboutUsPage;
	public DeliveryInformationPage deliveryInformationPage;
	public PrivacyPolicyPage privacyPolicyPage;
	public TermsConditionsPage termsConditionsPage;
	public ReturnsPage returnsPage;
	public BrandsPage brandsPage;
	public AffiliatePage affiliatePage;
	public GiftCertificatePage giftCertificatePage;
	public SiteMapPage siteMapPage;
	public SpecialsPage specialsPage;
	public ChangePasswordPage changePasswordPage;
	public AccountLogoutPage accountLogoutPage;

	
	public Actions getActions(WebDriver driver) {
		return new Actions(driver);
	}
	
	public Properties swapPasswords(Properties prop) {
		String oldPassword = prop.getProperty("validPasswordTwo");
		String newPassword = prop.getProperty("validPasswordThree");
		prop.setProperty("validPasswordTwo", newPassword);
		prop.setProperty("validPasswordThree", oldPassword);
	    prop = CommonUtils.storePropertiesFile(prop);
	    return prop;
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public Actions clickKeyboradKeyMultipleTimes(Actions actions, Keys keyName, int noOfTimes) {
		for (int i = 1; i <= noOfTimes; i++) {
			actions.sendKeys(keyName).perform();
		}
		return actions;
	}
	
	public Actions typeTextUsingActions(Actions actions, String text) {
		actions.sendKeys(text).perform();
		return actions;
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public void navigateBackInBrowser(WebDriver driver) {
		driver.navigate().back();
	}
	
	public String getBaseURL() {
		prop = CommonUtils.loadPropertiesFile();
		return prop.getProperty("appURL");
	}
	
	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}



}



	








