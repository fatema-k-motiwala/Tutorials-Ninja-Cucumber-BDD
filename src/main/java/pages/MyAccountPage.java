package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class MyAccountPage extends RootPage {

	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInformation;

	@FindBy(linkText = "Subscribe / unsubscribe to newsletter")
	private WebElement subscribeUnsubscribeToNewsletterOption;


	public NewsLetterPage clickOnSubscribeUnsubscribeToNewsletterOption() {
		elementUtilities.clickOnElement(subscribeUnsubscribeToNewsletterOption);
		return new NewsLetterPage(driver);
	}

	public boolean didWeNavigateToMyAccountPage() {
		return elementUtilities.isElementDisplayed(editYourAccountInformation);
	}
	
	public MyAccountInformationPage clickOnEditYourAccountInformation() {
		elementUtilities.clickOnElement(editYourAccountInformation);
		return new MyAccountInformationPage(driver);
	}


}
