package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	WebElement firstname;

	@FindBy(id = "input-lastname")
	WebElement lastname;

	@FindBy(id = "input-email")
	WebElement email;

	@FindBy(id = "input-telephone")
	WebElement telephone;

	@FindBy(id = "input-password")
	WebElement password;

	@FindBy(id = "input-confirm")
	WebElement confirmPassword;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	WebElement yesNewsletterOption;

	@FindBy(name = "agree")
	WebElement privacyPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueButton;

	public void enterFirstname(String firstnameText) {
		firstname.sendKeys(firstnameText);
	}
	public void enterLastname(String lastnameText) {
		lastname.sendKeys(lastnameText);
	}
	public void enterEmail(String emailText) {
		email.sendKeys(emailText);
	}
	public void enterTelephone(String telephoneText) {
		telephone.sendKeys(telephoneText);
	}
	public void enterPassword(String passwordText) {
		password.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPassword.sendKeys(confirmPasswordText);
	}
	
	public void selectYesNewsletterOption() {
		yesNewsletterOption.click();
	}
	public void selectprivacyPolicy() {
		privacyPolicy.click();
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	
	
}
