package utils;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementUtilities {

	WebDriver driver;
//		Actions actions;
//		Select select;

	public ElementUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isElementSelected(WebElement element) {
		boolean b = false;
		if (isElementDisplayed(element)) {
			b = element.isSelected();
		}
		return b;
	}
	
	public String getElementDomAttribute(WebElement element, String attributeName) {
		return element.getDomAttribute(attributeName);
	}
	
	public void clickOnElement(WebElement element) {
		if (isElementDisplayed(element) && element.isEnabled()) {
			element.click();
		}
	}

	public void enterTextIntoElement(WebElement element, String text) {
		if (isElementDisplayed(element) && element.isEnabled() && element.getDomProperty("readonly") == null) {
			element.clear();
			element.sendKeys(text);
		}
	}

	public boolean isElementDisplayed(WebElement element) {
		boolean b = false;
		try {
			b = element.isDisplayed();
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	public String getElementText(WebElement element) {
		String elementText = "";
		if (isElementDisplayed(element)) {
			elementText = element.getText();
		}
		return elementText;
	}
	
	public String getElementDomProperty(WebElement element, String attributeName) {
		return element.getDomProperty(attributeName);
	}
}
