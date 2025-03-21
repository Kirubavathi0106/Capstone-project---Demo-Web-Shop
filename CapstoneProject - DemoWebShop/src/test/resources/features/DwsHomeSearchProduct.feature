Feature: Home Page Verification,Search Functionality and product details verification

  # Background:
  # Given the user is on the homepage
  @Registrationdetails
Scenario Outline: Successful Registration
   Given the user is on the homepage
   When user navigates to Registration page
   And user selects gender "<gender>"
   And user enters first name "<first_name>"
   And user enters last name "<last_name>"
   And user enters email "<email>"
   And user enters password "<password>"
   And user enters confirm password "<confirm_password>"
   And user clicks on the register button
   Then user should see a success message 
   And user clicks on the logout button  


Examples:
    | first_name | last_name | email                        | password  | confirm_password | gender |
    | Kiruba     | Vathi     | Kirubavathi77345@gmail.com   | Kiruba@22 | Kiruba@22        | Female |
    #| Sanjiv     | Sai      | sanjiv@gmail.com             | Sanjiv@123 | sanjiv@1234     | Male   |
  

  @ValidLogin
  Scenario Outline: Login with valid credentials
    Given the user is on the homepage
    When the user clicks on login link
    And the user enters email "<email>" and password "<password>"
    And the user clicks on login button
    Then the welcome message and logout should be displayed on home page

  Examples:
    | email                    | password   |
    | kirubavathi14@gmail.com   | Kiruba@22  |

  @verifyhomepage
  Scenario: Verify the display of major sections on the home page
    Given the user is on the homepage
    When user verifies the Featured Products
    And user verifies the search bar
    #Then user verifies the presence of Top Products section

  @VerifySearchBar
  Scenario: Search for a product using the search bar
    Given the user is on the homepage
    When the user enters "Smartphone" in the search box
    And clicks the search button
    Then the search results for "Smartphone" should be displayed

  @SearchFunctionality
  Scenario Outline: Search and apply filters for different products
    Given the user is on the homepage
    When the user enters "<Product Name>" in the search box
    And clicks the search button
    And the user enables advanced search
    And the user selects category "<Category>"
    And the user sets the price range from <Min Price> to <Max Price>
    And the user clicks on the search button
    Then the search results for "<Product Name>" should be displayed within the filters and clicked
    And user clicks on Add to Cart button 
    And user verifies that the product is successfully added to the cart 
    

  Examples:
    | Product Name | Category                     | Min Price | Max Price |
    | Health Book  | Books                        | 5         | 10        |
    | Smartphone   | Electronics >> Cell phones   | 100         | 500       |


  @TestingProductDetails
  Scenario: Validate product details on the product details page
    Given the user on product details page
    When the product title is displayed
    And the product description should be displayed
    And the product price should be displayed
    And the product image should be displayed
    And the product availability should be displayed
    And user click on Add to Wishlist button
    And user goes to wishlist page
    Then the user verifies that the product is present in the wishlist
    
    
