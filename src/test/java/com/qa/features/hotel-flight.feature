Feature: Hotel and flight user search

  As an tester
  I want to be able to compare data from one environment to another
  So that I can validate data originating from two different database are the same


  Scenario:  User searches for Flight and Hotel
    Given user launches Hotel.com
    And navigate to Packages and Flights
    Then the links in the top banner should be displayed
      | Flights      |
      | Things to Do |

    When user searches for a Flight and Hotel Packages
      | Origin City    | Destination City | Departing Date | Returning Date | Travelers | Rooms | Airline Class |
      | Washington, DC | Miami            | 1              | 6              | 2         | 1     | Business      |

    And the Property Class filter is selected for 4 stars
    And the Neighborhood filter is set to Miami
    Then verify the Neighborhood filter was set to Miami




    