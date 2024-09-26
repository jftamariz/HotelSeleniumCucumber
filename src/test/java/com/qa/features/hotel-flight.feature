Feature: Hotel and flight user search

  As an tester
  I want to be able to compare data from one environment to another
  So that I can validate data originating from two different database are the same


  Scenario:  User searches for Flight and Hotel
    Given user launches Hotel.com
    When user searches by destination, accommodations or landmarks
      | Going To | Departing in N days from today | Trip Duration | Travelers Adults | Travelers Children |
      | Miami    | 30                             | 7             | 2                | 0                  |
    And the Property Class filter is selected for 4 stars
#    And the Neighborhood filter is set to Miami
#    Then verify the Neighborhood filter was set to Miami




    