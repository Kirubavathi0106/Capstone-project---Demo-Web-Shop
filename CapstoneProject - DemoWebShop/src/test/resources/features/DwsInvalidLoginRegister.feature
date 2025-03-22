Feature: Login and Registration Functionality

Background: 
   Given user on homepage of Website

@InvalidRegistration
Scenario Outline: Invalid Registration
   When user navigates to Registration page
   And user enters first name "<first_name>"
   And user enters last name "<last_name>"
   And user enters email "<email>"
   And user enters password "<password>"
   And user enters confirm password "<confirm_password>"
   And user selects gender "<gender>"
   And user clicks on the register button
   Then user should see an error message "<errormessage>"

Examples:
    | first_name | last_name | email                    | password  | confirm_password| gender| errormessage                     |
    | Kiruba     | Vathi     | Email@com                | Test@123  | Test@123         | Female | Wrong email format                |
    #| Kiruba     | Vathi     | Kirubavathi04@gmail.com | Test@123  | WrongPass        | Female | Passwords do not match            |
    #| Kiruba     | Vathi     | Kirubavathi04@gmail.com | 123       | 123              | Female | Password must be at least 6 chars |


@InvalidPassword
Scenario Outline: Login with invalid credentials
   When the user clicks on login link
   And the user enters email "<email>" and invalid password "<password>"
   And the user clicks on login button
   Then an error message should be displayed saying "Login was unsuccessful. Please correct the errors and try again"

Examples:
    | email                | password  |
    | Ramya01@gmail.com    | &%Pass   |

@InvalidUsername
Scenario Outline: Login with invalid email
   When the user clicks on login link
   And the user enters an invalid email "<email>" and password "<password>"
   And the user clicks on login button
   Then an error message should be displayed saying "Login was unsuccessful. Please correct the errors and try again"
Examples:
    | email         | password  |
    | Keerthi@gmail.com  | Ramya@123 |

@PasswordRecovery
Scenario Outline: Recovering the Password
   When the user clicks on login link
   And the user Click on Forgot Password link
   And the user Enter "<Email>" in the password recovery field
   And the user clicks on Recover button
   Then the user should see a recovery message "<Recovery Message>"

Examples:
    | Email                    | Recovery Message                                     |
    | Kirubavathi14@gmail.com  | Email with instructions has been sent to you.      |
    
     @InvalidSearch
   Scenario: Search with no matching results
     When the user enters "XYZPhone" in the search bar
     And clicks the search button
     Then a message "No products found" should be displayed
