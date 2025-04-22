package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

	WebDriver driver;

	public AccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	WebElement logoutOption;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
	WebElement successBreadcrumb;

	@FindBy(xpath = "//a[text()='Continue']")
	WebElement continueButton;

	public boolean isUserLoggedIn() {
		return logoutOption.isDisplayed();
	}
	
	public boolean didWeNavigateToAccountSuccessPage() {
		return successBreadcrumb.isDisplayed();
	}
	
	public void clickOnContinueButton() {
		continueButton.click();
	}
	
	
}
