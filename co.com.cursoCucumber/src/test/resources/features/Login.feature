
Feature: Log in  Functionality
  In order to do internet Banking
  As a valid parabank Customer
  I want to log in succesfully

  Scenario: Log in successfully
    Given I am in the  login page of the paraBank Application
    When I enter valid credentials
    |tautester|password|nombrecompleto|
    Then I should be  taken to the Overview page
    
  	