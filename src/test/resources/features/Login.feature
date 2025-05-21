Feature: Verify Login functionality

Scenario: Verify Login with valid credentials
Given User navigates to Login page
When User enters valid email and valid password into the fields
And User clicks on Login button
Then User should get logged in successfully
And User should be taken to My Account page

Scenario: Verify Login with invalid credentials
Given User navigates to Login page
When User enters invalid email and invalid password into the fields
And User clicks on Login button
Then User should not get logged in
And User should get a proper warning message

Scenario: Verify Login with invalid email and valid password
Given User navigates to Login page
When User enters invalid email and valid password into the fields
And User clicks on Login button
Then User should not get logged in
And User should get a proper warning message

Scenario: Verify Login with valid email and invalid password
Given User navigates to Login page
When User enters valid email and invalid password into the fields
And User clicks on Login button
Then User should not get logged in
And User should get a proper warning message

Scenario: Verify Login with providing any credentials
Given User navigates to Login page
When User does not enter email and password into the fields
And User clicks on Login button
Then User should not get logged in
And User should get a proper warning message

Scenario: Verify Forgort Password functionality on Login page
Given User opens Home Page
When User clicks on My Account dropmenu from Home Page
And User selects Login option from menu
Then Forgot Password option should be available on the page

Scenario: Verify Loginning into the Application using keyboard keys
Given User navigates to Login page
When User logs into the Application using keyboard keys
Then User should get logged in successfully
And User should be taken to My Account page

Scenario: Verify text fields in Login page have placeholder text
Given User opens Home Page
When User clicks on My Account dropmenu from Home Page
And User selects Login option from menu
Then Email and Password fields should have placeholder text

Scenario: Verify Logging into the Application and browser back
Given User navigates to Login page
When User Logins into the Application
And User clicks on browser back option
Then User should hot logout

Scenario: Verify the number of unsuccesfull login attempts
Given User navigates to Login page
When User enters invalid email credentials into the fields 6 times
Then User should get a warning for exceeding attempts

Scenario: Verify Text entered into password field is toggled to hide its visibility
Given User opens Home Page
When User clicks on My Account dropmenu from Home Page
And User selects Login option from menu
Then Password field should be toggled to hide its visibility

Scenario: Verify password entered into password field is not copyable
Given User navigates to Login page
When User enters text into Password field
And User copies the text from the Password field
Then Password text should not copyable

Scenario: Verify Password is not visible in Page Source
Given User navigates to Login page
When User enters random text into Password field
Then Password text should not visible in Page Source

Scenario: Verify Logging into the Application after changing password
Given User navigates to Login page
When User Logs into the Application
And User chagnes the Password
And User Logout from the Application
Then User should not be able to login with old password
And User should be able to login with new password

Scenario: Verify navigating to different pages from Login page
Given User opens Home Page
When User clicks on My Account dropmenu from Home Page
And User selects Login option from menu
Then User should be able to navigate to different pages from Login page

Scenario: Verify different ways of navigating to Login page
Given User opens Home Page
When User clicks on My Account dropmenu from Home Page
And User selects Login option from menu
Then User should be able to navigate to Login page in different waysl

Scenario: Verify Register Account Page Breadcrumb URL Title and Heading
Given User opens Home Page
When User clicks on My Account dropmenu from Home Page
And User selects Login option from menu
Then ProperPage Breadcrumb URL Title and Heading for login page should be displayed

@trynow
Scenario: Verify UI of Login page
Given User opens Home Page
When User clicks on My Account dropmenu from Home Page
And User selects Login option from menu
Then Proper UI for Login page should be displayed

#Scenario: Verify Register Account functionality in all supported environments
#Given User navigates to Register Account Page
#When User enters below fields
#|firstname 	|Fatema		|
#|lastname 	|Motiwala	|
#|telephone 	|1234567890	|
#|password 	|12345		|
#And User selects Privacy Policy Field
#And User clicks on Continue button
#Then User account should get logged in
#And User should be taken to Account Success Page
#And Proper details should be displayed on the Account Sucess Page
#When User clicks on Continue button on Account Sucess Page
#Then User should be navigated to My Account Page



