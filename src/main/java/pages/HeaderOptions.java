package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class HeaderOptions extends RootPage {

	WebDriver driver;

	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Checkout']")
	private WebElement checkoutOption;

	@FindBy(linkText = "Qafox.com")
	private WebElement logo;

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;

	@FindBy(name = "search")
	private WebElement searchBox;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "My Account")
	private WebElement myAccountOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(linkText = "Logout")
	private WebElement logoutOption;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(xpath = "//i[@class='fa fa-phone']")
	private WebElement phoneIcon;

	@FindBy(xpath = "//i[@class='fa fa-heart']")
	private WebElement heartIcon;

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][contains(text(), 'Wish List')]")
	private WebElement wishList;

	@FindBy(xpath = "//i[@class='fa fa-shopping-cart']")
	private WebElement shoppingCartHeaderIcon;

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement shoppingCartHeaderOption;

	@FindBy(xpath = "//i[@class='fa fa-share']")
	private WebElement checkoutIcon;

	public String getSearchBoxPlaceHolderText() {
		return elementUtilities.getElementDomAttribute(searchBox, "placeholder");
	}
	public MyAccountPage selectMyAccountOption() {
		elementUtilities.clickOnElement(myAccountOption);
		return new MyAccountPage(driver);
	}
	

	public void enterProductIntoSearchBoxField(String product) {
		elementUtilities.enterTextIntoElement(searchBox, product);
	}

	public boolean isSearchBoxFieldDisplayed() {
		return elementUtilities.isElementDisplayed(searchBox);
	}

	public boolean isSearchButtonDisplayed() {
		return elementUtilities.isElementDisplayed(searchButton);
	}

	public boolean areSearchBoxFieldAndSearchButtonDisplayed() {
		return isSearchBoxFieldDisplayed() && isSearchButtonDisplayed();
	}


	public LoginPage selectHeartIcon() {
		elementUtilities.clickOnElement(heartIcon);
		return new LoginPage(driver);
	}

	public LoginPage selectLoginOption() {
		elementUtilities.clickOnElement(loginOption);
		return new LoginPage(driver);
	}

	public void clickOnMyAccountDropMenu() {
		elementUtilities.clickOnElement(myAccountDropMenu);
	}

	public RegisterPage selectRegisterOption() {
		elementUtilities.clickOnElement(registerOption);
		return new RegisterPage(driver);
	}

	public RegisterPage navigateToRegisterPage() {
		clickOnMyAccountDropMenu();
		return selectRegisterOption();
	}

	public HomePage selectLogo() {
		elementUtilities.clickOnElement(logo);
		return new HomePage(driver);
	}


	public boolean isLogoutOptionDisplayed() {
		return elementUtilities.isElementDisplayed(logoutOption);

	}

	public boolean isLoginOptionDisplayed() {
		return elementUtilities.isElementDisplayed(loginOption);

	}

	public LoginPage navigateToLoginPage() {
		clickOnMyAccountDropMenu();
		return selectLoginOption();
	}


}
