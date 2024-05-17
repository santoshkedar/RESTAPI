Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify whether Place is being successfully added using AddPlaceAPI
		Given Add Place Payload with "<name>" "<language>" "<address>"
		When  user call "AddPlaceAPI" with "POST" http request
		Then  the API call get status code as 200
		And   the "status" in response body is "OK"
		And   the "scope" in response body is "APP"
		And   verify place_Id created maps to "<name>" using "getPlaceAPI"
		
	Examples:
	|name     |language  |address      |
	|Santosh  | Marathi  |Pune India   |
#|Kedar    | English  |Mumbai India |

@DeletePlace @Regression
Scenario: Verify if Delete Place API is working successfully
Given DeletePlaceAPI Payload
When  user call "DeletePlaceAPI" with "POST" http request
Then  the API call get status code as 200
And   the "status" in response body is "OK"