package stepdefinitions;

import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Search {
	
	WebDriver driver;
	Properties prop;
	
	@Given("User is on the Home page")
	public void user_is_on_the_home_page() {
	    driver = DriverFactory.getDriver();
	    prop=CommonUtils.loadPropertiesFile();
	}

	@When("User enters existing product into the Search box field")
	public void user_enters_existing_product_into_the_search_box_field() {
	     driver.findElement(By.name("search")).sendKeys(prop.getProperty("existingProduct"));
	}

	@And("User clicks on Search button")
	public void user_clicks_on_search_button() {
	     driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
	}

	@Then("Product should be displayed in the search results")
	public void product_should_be_displayed_in_the_search_results() {
	     Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}

	@When("User enters non existing product into the Search box field")
	public void user_enters_non_existing_product_into_the_search_box_field() {
	     driver.findElement(By.name("search")).sendKeys(prop.getProperty("nonexistingProduct"));
	}

	@Then("Proper message should be displayed in the search results")
	public void proper_message_should_be_displayed_in_the_search_results() {
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(expectedMessage, driver.findElement(By.xpath("//input[@id='button-search']//following-sibling::p")).getText());
	}




}
