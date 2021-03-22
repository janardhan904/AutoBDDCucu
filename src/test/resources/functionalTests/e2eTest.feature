Feature: Automated E2E Tests

  @tag1
  Scenario: User place an order by purchasing item from search
    Given user is on HomePage
    When user search for "dress"
    And Choose to buy the second item
    And Adds the item to cart
    And Proceeds to checkout and Sign in to website using "janardhan.reddy75@gmail.com" and "janardhan@123"
    And Proceeds to checkout and Check terms of Service
    And select payment method and places the order
    And verify the order in order history

  @tag2
  Scenario: Update Personal Info
    Given user logged in to the website using "janardhan.reddy75@gmail.com" and "janardhan@123"
    When navigates to personal info page
    And updates name


