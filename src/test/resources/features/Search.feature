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

@trynow
Scenario: Verify Gird View when only one Product is displayed in search results
Given User is on the Home page
When User enters search product into the Search box field
And User clicks on Search button
And User clicks on Grid option
Then Single Product should be displayed in the Grid view
And All the Product options are working fine in Grid view































