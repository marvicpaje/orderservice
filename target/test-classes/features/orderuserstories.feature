Feature: Get an Order Number from Order
	As an ordering customer
	I want to get an Order Number from Orders using its orderNumber
	So that I can get info about the Order

Scenario Outline: Get an existing Order Number from Order
   Given Order and Menu services are running and Order's db has orderNumber, <orderNumber>, in it
   When The Order microservice receives a request for orderNumber, <orderNumber>
   Then The Order microservice should return order information for orderNumber, <orderNumber>

  Examples:
    | orderNumber  |
    | ORDER-123      | 
    | ORDER-456      | 
