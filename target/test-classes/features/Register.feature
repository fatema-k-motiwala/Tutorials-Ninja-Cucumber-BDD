Feature: Register Account Functionality

Scenario: Verify Registering Account with Mandatory Fields
Given User navigates to Register Account Page
When User enters below fields
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|1234567890	|
|password 	|12345		|
And User selects Privacy Policy Field
And User clicks on Continue button
Then User account should get logged in
And User should be taken to Account Sucess Page
And Proper details should be displayed on the Account Sucess Page
When User clicks on Continue button on Account Sucess Page
Then User should be navigated to My Account Page

Scenario: Verify Registering Account by filling all the fields
Given User navigates to Register Account Page
When User enters below fields
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|1234567890	|
|password 	|12345		|
And User selects Yes option for Newsletter
And User selects Privacy Policy Field
And User clicks on Continue button
Then User account should get logged in
And User should be taken to Account Sucess Page

Scenario: Verify Warning Messages on Registering Account without filling mandatory fields
Given User navigates to Register Account Page
When User clicks on Continue button
Then Proper Warning Messages should be displayed

Scenario: Verify Registering Account by selecting Yes Newsletter field
Given User navigates to Register Account Page
When User enters below fields
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|1234567890	|
|password 	|12345		|
And User selects Yes option for Newsletter
And User selects Privacy Policy Field
And User clicks on Continue button
And User clicks on Continue button on Account Sucess Page
And User clicks on Subscribe or UnSubscribe to NewsLetter Option
Then Yes option in the newsletter page should be displayed as selected

Scenario: Verify Registering Account by selecting Yes Newsletter field
Given User navigates to Register Account Page
When User enters below fields
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|1234567890	|
|password 	|12345		|
And User selects No option for Newsletter
And User selects Privacy Policy Field
And User clicks on Continue button
And User clicks on Continue button on Account Sucess Page
And User clicks on Subscribe or UnSubscribe to NewsLetter Option
Then No option in the newsletter page should be displayed as selected

Scenario: Verify navigating to Register Account page from My Account drop menu
Given User opens Application URL in the browser
When User clicks on My Account dropmenu
And User selects Register option
Then User should be navigated to Register Account page

@trynow
Scenario: Verify navigating to Register Account page from Login page using button
Given User opens Application URL in the browser
When User clicks on My Account dropmenu
And User selects Login option
And User clicks on Continue button on Login Page
Then User should be navigated to Register Account page

@trynow
Scenario: Verify navigating to Register Account page using Right column options
Given User opens Application URL in the browser
When User clicks on My Account dropmenu
And User selects Login option
And Clicks on Register option from Right column options
Then User should be navigated to Register Account page

