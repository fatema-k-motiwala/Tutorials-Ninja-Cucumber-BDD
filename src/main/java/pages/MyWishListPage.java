package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class MyWishListPage extends RootPage {
	
	WebDriver driver;
	
	public MyWishListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	

}
