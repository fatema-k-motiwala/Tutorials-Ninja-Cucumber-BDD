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
And User should be taken to Account Success Page
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
And User should be taken to Account Success Page

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

Scenario: Verify navigating to Register Account page from Login page using button
Given User opens Application URL in the browser
When User clicks on My Account dropmenu
And User selects Login option
And User clicks on Continue button on Login Page
Then User should be navigated to Register Account page

Scenario: Verify navigating to Register Account page using Right column options
Given User opens Application URL in the browser
When User clicks on My Account dropmenu
And User selects Login option
And Clicks on Register option from Right column options
Then User should be navigated to Register Account page

Scenario: Verify registering account by providing mismatching passwords
Given User navigates to Register Account Page
When User enters the below fields
|firstname 				|Fatema			|
|lastname 				|Motiwala		|
|telephone 				|1234567890 |
|password				 	|12345			|
|confirmPassword 	|67890			|
And User selects Privacy Policy Field
And User clicks on Continue button
Then Proper warning messages regarding password mistmatch should be displayed

Scenario: Verify registering account by providing existing account details
Given User navigates to Register Account Page
When User enters below fields except email field
|firstname 				|Fatema			|
|lastname 				|Motiwala		|
|telephone 				|1234567890 |
|password				 	|12345			|
And User enters an existing email address into the email field
And User selects Privacy Policy Field
And User clicks on Continue button
Then Proper warning messages regarding account exists with this email should be displayed

Scenario: Verify registering account using invalid email address
Given User navigates to Register Account Page
When User enters below fields except email field
|firstname 				|Fatema			|
|lastname 				|Motiwala		|
|telephone 				|1234567890 |
|password				 	|12345			|
And User enters an invalid email address into the email field
And User selects Privacy Policy Field
And User clicks on Continue button
Then Proper warning message provide valid email address should be displayed

Scenario: Verify registering account using invalid phone number
Given User opens Application URL in the browser
When User navigates to Register Account Page
When User enters below fields
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|abcde	|
|password 	|12345		|
And User selects Privacy Policy Field
And User clicks on Continue button
Then Proper warning message to provide valid phone number should be displayed

Scenario: Verify registerig account using keyboard keys
Given User navigates to Register Account Page
When User fills all the below fields using keyboard keys
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|1234567890	|
|password 	|12345		|
And User selects newsletter and privacy policy field using keyboard keys
And User selects Continue button also using keyboard
Then User account should get logged in
And User should be taken to Account Success Page

Scenario: Verify all the fields in the Register Account page have proper placeholders
Given User opens Application URL in the browser
When User navigates to Register Account Page
Then Proper Placeholder texts should be displayed for all the text fields

Scenario: Verify mandatory fields on Regiter Account page are marked with * symbol
Given User opens Application URL in the browser
When User navigates to Register Account Page
Then All the mandatory fields in Register Account page should be marked with * symbol

Scenario: Verify mandatory fields in Register Account page are not accepting only spaces
Given User navigates to Register Account Page
When User enters only spaces into all the mandatory fields
And User selects Privacy Policy Field
And User clicks on Continue button
Then Warning message should be displayed for these Mandatory fields

Scenario Outline: Verify Registering Account using passwords not following complexity standards
Given User navigates to Register Account Page
When User enters below fields except password field
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|1234567890	|
And User enters <password> which is not following password complexity standards
And User selects Privacy Policy Field
And User clicks on Continue button
Then Proper Warning messages should be displayed about password complexity not being followed
Examples:
|password |
|12345		|
|abcdefghi|
|abcd1234 |
|abcd123$ |
|ABCD456! |

Scenario: Verify fields in the Register Account page are according to the Client Requirements
Given User opens Application URL in the browser
And User navigates to Register Account Page
Then All the fields in the Register Account page are designed according to the Client Requirements

Scenario: Verify leading and trailing spaces entered while registering account are trimmed
Given User navigates to Register Account Page
When User enters below fields with leading and trailing spaces
|firstname 	|       Fatema		  |
|lastname 	|       Motiwala	  |
|telephone 	|       1234567890	|
|password 	|12345		|
And User selects Privacy Policy Field
And User clicks on Continue button
And User should be taken to Account Success Page
Then Leading and trailing spaces entered into the fields should get trimmed

Scenario: Verify Privacy Policy field is not selected by default
Given User opens Application URL in the browser
When User navigates to Register Account Page
Then Privacy Policy field should not be selected by defaultl

Scenario: Verify Registering Account without selecting Privacy Policy Field
Given User navigates to Register Account Page
When User enters below fields
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|1234567890	|
|password 	|12345		|
And User clicks on Continue button
Then Proper warning messages should be displayed for selecting Privacy Policy

Scenario: Verify Password entered into the passwords fields is toggled to hide its visibility
Given User opens Application URL in the browser
When User navigates to Register Account Page
Then Password text in password fields is toggled to hide its visibility

Scenario: Verify navigating to other pages from Register
Given User opens Application URL in the browser
When User navigates to Register Account Page
Then User should be able to navigate to other pages from Register Account page

Scenario: Verify registering account by not filling password confirm field
Given User navigates to Register Account Page
When User enters below fields except password field
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|1234567890	|
And User enters password only into the Password but not into the password confirm field
And User selects Privacy Policy Field
And User clicks on Continue button
Then Proper warning messages about password mismatch should displayed

Scenario: Verify Register Account Page Breadcrumb URL Title and Heading
Given User opens Application URL in the browser
When User navigates to Register Account Page
Then ProperPage Breadcrumb URL Title and Heading for register account page should be displayed

Scenario: Verify UI of Register Account page
Given User opens Application URL in the browser
When User navigates to Register Account Page
Then Proper UI for Register Account page should be displayed

@trynow
Scenario: Verify Register Account functionality in all supported environments
Given User navigates to Register Account Page
When User enters below fields
|firstname 	|Fatema		|
|lastname 	|Motiwala	|
|telephone 	|1234567890	|
|password 	|12345		|
And User selects Privacy Policy Field
And User clicks on Continue button
Then User account should get logged in
And User should be taken to Account Success Page
And Proper details should be displayed on the Account Sucess Page
When User clicks on Continue button on Account Sucess Page
Then User should be navigated to My Account Page









