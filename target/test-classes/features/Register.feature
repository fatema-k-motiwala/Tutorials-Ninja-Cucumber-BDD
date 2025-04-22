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
