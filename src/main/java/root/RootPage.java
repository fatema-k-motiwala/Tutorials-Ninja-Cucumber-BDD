package root;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.HeaderOptions;
import pages.RightColumnOptions;
import utils.ElementUtilities;

public class RootPage {

	WebDriver driver;
	public ElementUtilities elementUtilities;

	public RootPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtilities = new ElementUtilities(driver);
	}
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement pageLevelWarning;	

	public String getPageLevelWarning() {
		return elementUtilities.getElementText(pageLevelWarning);
	}
	
	public RightColumnOptions getRightColumnOptions() {
		return new RightColumnOptions(driver);	
		}

	public HeaderOptions getHeaderOptions() {
		return new HeaderOptions(driver);	
		}

}
