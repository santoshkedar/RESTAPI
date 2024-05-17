Feature: Validating API through GraphQL
@CreateLocation @Regression
Scenario Outline: Verify whether Create Location query is being successfully executed using GraphQL
		Given Create location Payload with "<name>" "<type>" "<dimension>"
		When  user call "GraphQL" with "POST" http request
		Then  the API call get status code as 200
		And   the locationID is created
		And   verify locationID created maps to "<name>" "<type>" using "GraphQL"
		
	Examples:
	|name     |type      |dimension    |
	|Pune     |IT City   |1000         |

@CreateCharacter @Regression
Scenario Outline: Verify whether Create Character query is being successfully executed using GraphQL
		Given Create Character Payload with "<CharacterName>" "<type>" "<status>" "<species>" "<gender>" "<image>"
		When  user call "GraphQL" with "POST" http request
		Then  the API call get status code as 200
		And   the characterID is created
		And   verify CharacterID created maps to "<CharacterName>" "<type>" "<status>" using "GraphQL"
		
	Examples:
	|CharacterName     |type      |status    |species |gender|image    |
	|Iron Man          |Marvel    |Active    |Human   |male  |Iron.jpeg|
 
@CreateEpisode @Regression
Scenario Outline: Verify whether Create Episode query is being successfully executed using GraphQL
		Given Create Episode Payload with "<EpisodeName>" "<Air Date>" "<Episode ID>"
		When  user call "GraphQL" with "POST" http request
		Then  the API call get status code as 200
		And   the episodeID is created
		And   verify episodeID created maps to "<EpisodeName>" "<Air Date>" using "GraphQL"
		
	Examples:
	|EpisodeName         |Air Date      |Episode ID    |
	|Iron Man Marvel     |01012010      |1             |
	
@DeleteLocation @Regression
Scenario: Verify if Delete Location GraphQL schema is working successfully
Given Delete Location GraphQL Payload
When  user call "GraphQL" with "POST" http request
Then  the API call get status code as 200

@DeleteCharacter @Regression
Scenario: Verify if Delete Character GraphQL schema is working successfully
Given Delete Character GraphQL Payload
When  user call "GraphQL" with "POST" http request
Then  the API call get status code as 200

@DeleteEpisode @Regression
Scenario: Verify if Delete Episode GraphQL schema is working successfully
Given Delete Episode GraphQL Payload
When  user call "GraphQL" with "POST" http request
Then  the API call get status code as 200


