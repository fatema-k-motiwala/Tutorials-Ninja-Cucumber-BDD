package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class RegisterPage extends RootPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstname;

	@FindBy(id = "input-lastname")
	private WebElement lastname;

	@FindBy(id = "input-email")
	private WebElement email;

	@FindBy(linkText = "login page")
	private WebElement loginPageOption;

	@FindBy(id = "input-telephone")
	private WebElement telephone;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(id = "input-confirm")
	private WebElement confirmPassword;

	@FindBy(css = "label[for='input-firstname']")
	private WebElement firstNameLabel;

	@FindBy(css = "label[for='input-lastname']")
	private WebElement lastNameLabel;

	@FindBy(css = "label[for='input-email']")
	private WebElement emailLabel;

	@FindBy(css = "label[for='input-telephone']")
	private WebElement telephoneLabel;

	@FindBy(css = "label[for='input-password']")
	private WebElement passwordLabel;

	@FindBy(css = "label[for='input-confirm']")
	private WebElement passwordConfirmLabel;

	@FindBy(css = "div[class='pull-right']")
	private WebElement privacyPolicyLabel;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;

	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement noNewsletterOption;

	@FindBy(name = "agree")
	private WebElement privacyPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	@FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div")
	private WebElement passwordConfirmationWarning;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement registerPageBreadcrumb;
	
	public Boolean didWeNavigatetoRegisterpage() {
		return elementUtilities.isElementDisplayed(registerPageBreadcrumb);
	}
	
	public void clearFirstNameField() {
		elementUtilities.clearTextfromElement(firstname);
	}

	public void clearlastNameField() {
		elementUtilities.clearTextfromElement(lastname);
	}
	
	public void clearEmailField() {
		elementUtilities.clearTextfromElement(email);
	}

	public void clearTelephoneField() {
		elementUtilities.clearTextfromElement(telephone);
	}

	public void clearPasswordField() {
		elementUtilities.clearTextfromElement(password);
	}
	
	public Boolean isFirstNameWarningDisplayed() {
		return elementUtilities.isElementDisplayed(firstNameWarning);
	}

	public Boolean isPasswordWarningDisplayed() {
		return elementUtilities.isElementDisplayed(passwordWarning);
	}

	public Boolean isTelephoneWarningDisplayed() {
		return elementUtilities.isElementDisplayed(telephoneWarning);
	}

	public Boolean isLastNameWarningDisplayed() {
		return elementUtilities.isElementDisplayed(lastNameWarning);
	}

	public Boolean isEmailWarningDisplayed() {
		return elementUtilities.isElementDisplayed(emailWarning);
	}
	
	public String getfirstNameWarning() {
		return elementUtilities.getElementText(firstNameWarning);
	}

	public String getlastNameWarning() {
		return elementUtilities.getElementText(lastNameWarning);
	}

	public String getemailWarning() {
		return elementUtilities.getElementText(emailWarning);
	}

	public String gettelephoneWarning() {
		return elementUtilities.getElementText(telephoneWarning);
	}

	public String getpasswordWarning() {
		return elementUtilities.getElementText(passwordWarning);
	}

	public String getPasswordConfirmationWarning() {
		return elementUtilities.getElementText(passwordConfirmationWarning);
	}

	public void enterFirstname(String firstnameText) {
		elementUtilities.enterTextIntoElement(firstname, firstnameText);
	}

	public void enterLastname(String lastnameText) {
		elementUtilities.enterTextIntoElement(lastname, lastnameText);
	}

	public void enterEmail(String emailText) {
		elementUtilities.enterTextIntoElement(email, emailText);
	}

	public void enterTelephone(String telephoneText) {
		elementUtilities.enterTextIntoElement(telephone, telephoneText);
	}

	public void enterPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(password, passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordText) {
		elementUtilities.enterTextIntoElement(confirmPassword, confirmPasswordText);
	}

	public void selectYesNewsletterOption() {
		elementUtilities.clickOnElement(yesNewsletterOption);
	}

	public void selectNoNewsletterOption() {
		elementUtilities.clickOnElement(noNewsletterOption);
	}

	public void selectprivacyPolicy() {
		elementUtilities.clickOnElement(privacyPolicy);
	}

	public AccountSuccessPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new AccountSuccessPage(driver);
	}

	public String getEmailValidationMessage() {
		return elementUtilities.getElementDomProperty(email, "validationMessage");
	}


	public String getFirstNamePlaceHolderText() {
		return elementUtilities.getElementDomAttribute(firstname, "placeholder");
	}

	public String getLasttNamePlaceHolderText() {
		return elementUtilities.getElementDomAttribute(lastname, "placeholder");
	}

	public String getEmailPlaceHolderText() {
		return elementUtilities.getElementDomAttribute(email, "placeholder");
	}

	public String getTelephonePlaceHolderText() {
		return elementUtilities.getElementDomAttribute(telephone, "placeholder");
	}

	public String getPasswordDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(password, attributeName);
	}

	public String getConfirmPasswordDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(confirmPassword, attributeName);
	}

	public WebElement getFirstNameLabel() {
		return firstNameLabel;
	}

	public WebElement getLastNameLabel() {
		return lastNameLabel;
	}

	public WebElement getEmailLabel() {
		return emailLabel;
	}

	public WebElement getTelephoneLabel() {
		return telephoneLabel;
	}

	public WebElement getPasswordLabel() {
		return passwordLabel;
	}

	public WebElement getPasswordConfirmLabel() {
		return passwordConfirmLabel;
	}

	public WebElement getPrivacyPolicyLabel() {
		return privacyPolicyLabel;
	}

	public String getFirstNameCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(firstname, propertyName);
	}

	public String getPasswordCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(password, propertyName);

	}

	public LoginPage selectLoginPageOption() {
		elementUtilities.clickOnElement(loginPageOption);
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterPageBreadcrumb() {
		elementUtilities.clickOnElement(registerPageBreadcrumb);
		return new RegisterPage(driver);
	}

	public String getPasswordConfirmCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(confirmPassword, propertyName);
	}

	public String getContinueButtonCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(continueButton, propertyName);
	}

	public String getTelephoneCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(telephone, propertyName);
	}

	public String getLastNameCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(lastname, propertyName);
	}

	public String getEmailCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(email, propertyName);
	}

	public Boolean isPrivacyPolicySelected() {
		return elementUtilities.isElementSelected(privacyPolicy);
	}

	public AccountSuccessPage registeringAnAccount(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText) {
		enterFirstname(firstNameText);
		enterLastname(lastNameText);
		enterEmail(emailText);
		enterTelephone(telephoneText);
		enterPassword(passwordText);
		enterConfirmPassword(passwordText);
		selectprivacyPolicy();
		return clickOnContinueButton();
	}

	
}
