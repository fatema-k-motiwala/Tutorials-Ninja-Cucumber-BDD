Feature: Verify Search functionality

Scenario: Verify searching with existing product
Given User is on the Home page
When User enters existing product into the Search box field
And User clicks on Search button
Then Product should be displayed in the search results

Scenario: Verify searching with non existing product
Given User is on the Home page
When User enters non existing product into the Search box field
And User clicks on Search button
Then Proper message should be displayed in the search results

Scenario: Verify searching without providing any product
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
Then Proper message should be displayed in the search results

Scenario: Verify searching for a product after logging in
Given User is on the Home page
And User is logged into the Application
When User enters existing product into the Search box field
And User clicks on Search button
Then Product should be displayed in the search results

Scenario: Verify searching using search criteria which results in multiple products
Given User is on the Home page
When User enters search product into the Search box field
And User clicks on Search button
Then Multiple product details should be displayed in the search results

Scenario: Verify Search box field has placeholder text
Given User is on the Home page
When User does not enter any product into the Search box field
Then Search box field should have placeholder text

Scenario: Verify fields in Search result page have placeholder text
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
Then Fields on Search results page should have placeholder text

Scenario: Verify searching using Search Criteria field in Search results page
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
And User enters existing product into the Search Criteria field on Search results page
And User clicks on Search button on Search results page
Then Product should be displayed in the search results

Scenario: Verify searching using product description text
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
And User enters product description text into the Search Criteria field on Search results page
And User selects Search in Product Descriptions field
And User clicks on Search button on Search results page
Then Product with searched description text should be displayed in the search results

Scenario: Verify searching by selecting Product Category
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
And User enters a Product from a category into the Search criteria field
And User selects correct Product category
And User clicks on Search button on Search results page
Then Product from the selected category should be displayed in the search results

Scenario: Verify searching by selecting Wrong Product Category
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
And User enters a Product from a category into the Search criteria field
And User selects Wrong Product category
And User clicks on Search button on Search results page
Then Proper message should be displayed in the search results

Scenario: Verify searching by selecting Parent categories
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
And User enters a Product from a category into the Search criteria field
And User selects Parent category
And User clicks on Search button on Search results page
Then Proper message should be displayed in the search results

Scenario: Verify searching by selecting sub categories
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
And User enters a Product from a category into the Search criteria field
And User selects Parent category
And User selects a sub category
And User clicks on Search button on Search results page
Then Product from the selected category should be displayed in the search results

Scenario: Verify List View when only one Product is displayed in search results
Given User is on the Home page
When User enters search product into the Search box field
And User clicks on Search button
And User clicks on List option
Then Single Product should be displayed in the List view
And All the Product options are working fine

Scenario: Verify Gird View when only one Product is displayed in search results
Given User is on the Home page
When User enters search product into the Search box field
And User clicks on Search button
And User clicks on Grid option
Then Single Product should be displayed in the Grid view
And All the Product options are working fine in Grid view

Scenario: Verify List View when multiple Products are displayed in search results
Given User is on the Home page
When User enters search product with multiple results into the Search box field 
And User clicks on Search button
And User clicks on List option
Then Multiple Product should be displayed in the List view

Scenario: Verify Grid View when multiple Products are displayed in search results
Given User is on the Home page
When User enters search product with multiple results into the Search box field 
And User clicks on Search button
And User clicks on Grid option
Then Multiple Product should be displayed in the Grid view
And All the Product options are working fine in Grid view

Scenario: Verify navigating to Product Compare Page from Search Results Page
Given User is on the Home page
When User enters search product into the Search box field
And User clicks on Search button
And User clicks on Product Compare link
Then User should be navigated to Product Compare page

Scenario: Verify user is able to sort products in Search Results page
Given User is on the Home page
When User enters search product with multiple results into the Search box field 
And User clicks on Search button
Then Products should get sorted properly on selecting different sorting options

Scenario: Verify user is able to limit the number of produts to be displayed in search results
Given User is on the Home page
When User enters search product with multiple results into the Search box field 
And User clicks on Search button
And User selects the number of products to be displayed in search results
Then Selected number of products should be displayed in search results page 

Scenario: Verify search box field and search button are displayed on all pages of application
Given User is on the Home page
When User navigates to all pages of application
Then Search box field and search button should be displayed on all pages

Scenario: Verify navigating to search page from sitemap page
Given User is on the Home page
When User navigates to sitemap page
And User clicks on search option
Then User should be navigated to search page

Scenario: Verify breadcrumb option of Search page
Given User is on the Home page
When User enters existing product into the Search box field
And User clicks on Search button
Then Breadcrumb option on the search page should work correctly

Scenario: Verify we can use all options of Search functionality using keyboard keys
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
Then All the operations on the search page should be performed with the keyboard keys

Scenario: Verify page heading, page url and page title of the search page
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
Then Proper page heading, page url and page title should be displayed

Scenario: Verify the UI of the Search page
Given User is on the Home page
When User does not enter any product into the Search box field
And User clicks on Search button
Then Proper UI should be displayed for the search functionality in search Page



















