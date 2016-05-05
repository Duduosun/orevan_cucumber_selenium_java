@api
Feature: Sample CRUD operations to test a RESTful Service

  Scenario: Get the list of colours
    When I get the list of colours
    Then the Items are "retrieved"
    And the colour collections contain colour name

# Test with @dummy tags  will fail as there is no real Service to test with.
  @dummy
  Scenario: Create an Item
    When I create an Item
      | itemType | itemName |
      | TABLET   | ipad     |
      | MOBILE   | iphone   |

    Then the Item is "created"

  @dummy
  Scenario: Update an Item
    When I update an Item
      | itemType | itemName |
      | TABLET   | tab pro  |
      | MOBILE   | note 5   |
    Then the Item is "updated"

  @dummy
  Scenario: Delete an Item
    When I delete an Item "tablet"
    Then the Item is "deleted"

