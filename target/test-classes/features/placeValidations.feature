Feature: Validating Place API's

Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "post" http request
	Then API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify Place_id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name    | language | address      		| 
	|AAhouse | English  | World cross center|
#	|BBhouse | Spanish  | Sea cross center	|