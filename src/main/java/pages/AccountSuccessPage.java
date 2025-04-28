package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class AccountSuccessPage extends RootPage {

	WebDriver driver;

	public AccountSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	private WebElement logoutOption;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
	private WebElement successBreadcrumb;

	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement continueButton;

	@FindBy(id ="content")
	private WebElement accountSuccessPageContent;
	
	public String getContent()
	{
		return elementUtilities.getElementText(accountSuccessPageContent);
	}
	
	public boolean isUserLoggedIn() {
		return elementUtilities.isElementDisplayed(logoutOption);
	}
	
	public boolean didWeNavigateToAccountSuccessPage() {
		return elementUtilities.isElementDisplayed(successBreadcrumb);
	}
	
	public MyAccountPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new MyAccountPage(driver);
	}
	
	
}
