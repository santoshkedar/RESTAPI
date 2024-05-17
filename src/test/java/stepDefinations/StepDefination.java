package stepDefinations;

import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils
{
	ResponseSpecification res;
	RequestSpecification requestBody;
	Response response;
	TestDataBuild bodyJson = new TestDataBuild();
	JsonPath js;
	static String place_id;
	static String location_id;
	static String character_ID;
	static String episode_ID;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException 
	{	
		//create Request body for add request
		//requestSpecification() method created to build request for common parameters 
		
		requestBody= given().spec(requestSpecification()).body(bodyJson.addPlacePayload(name,language,address));
	}
	
	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String method) 
	{
		//Enum class constructor will be called with value of resource which we pass
		
		APIResources resAPI= APIResources.valueOf(resource);
		
		res= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
			response = requestBody.when().post(resAPI.getResource()).then().spec(res).extract().response();
		else if (method.equalsIgnoreCase("GET"))
			response = requestBody.when().get(resAPI.getResource()).then().spec(res).extract().response();
	}
	
	@Then("the API call get status code as {int}")
	public void the_api_call_get_status_code_as(Integer int1) 
	{	
		assertEquals(response.getStatusCode(),200);
	}
	@Then("the {string} in response body is {string}")
	public void the_in_response_body_is(String key, String expectedValue) 
	{	
		//Use GeJsonpath method to retrive value from response for the key
		assertEquals(getJsonPath(response, key),expectedValue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException 
	{
		// Get the place id using getJsonPath method
				
		place_id = getJsonPath(response, "place_id");
				
		requestBody= given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		//Reused method 
		
		user_call_with_http_request(resource,"GET");
		
		String actualName = getJsonPath(response, "name");
		
		assertEquals(actualName,expectedName);
		
	}
	
	@Given("DeletePlaceAPI Payload")
	public void delete_place_api_payload() throws IOException 
	{
		requestBody= given().spec(requestSpecification()).body(bodyJson.deleteAPIBody(place_id));
	}
	
	@Given("Create location Payload with {string} {string} {string}")
	public void create_location_payload_with(String locationName, String type, String dimension) throws IOException 
	{
		requestBody= given().spec(requestSpecificationGraphQL()).body(bodyJson.addLocationPayload(locationName,type,dimension));
		
		//Reused method 
	}
		
	@Then("the locationID is created")
	public void the_location_id_is_created() 
	{
		location_id = getJsonPath(response, "data.createLocation.id");
	}
	
	@Then("verify locationID created maps to {string} {string} using {string}")
	public void verify_location_id_created_maps_to_using(String expectedname, String expectedType, String resource) throws IOException 
	{
			
		requestBody= given().spec(requestSpecificationGraphQL()).body(bodyJson.queryLocationPayload(location_id));
		
		//Reused method 
		
		user_call_with_http_request(resource,"POST");
		
		String actualName = getJsonPath(response, "data.location.name");
		String actualType = getJsonPath(response, "data.location.type");
		
		assertEquals(actualName,expectedname);
		assertEquals(actualType,expectedType);
	}
	
	
	@Given("Create Character Payload with {string} {string} {string} {string} {string} {string}")
	public void create_character_payload_with(String characterName, String type, String status, String species, String gender, String image) throws IOException 
	{
		requestBody= given().spec(requestSpecificationGraphQL()).body(bodyJson.addCharcterPayload(characterName,type,status,species,gender,image,location_id, location_id));
	}
	
	
	@Then("the characterID is created")
	public void the_characterID_id_is_created() 
	{
		character_ID = getJsonPath(response, "data.createCharacter.id");
	}
	
	@Then("verify CharacterID created maps to {string} {string} {string} using {string}")
	public void verify_character_id_created_maps_to_using(String expectedName, String expectedType, String expectedStatus, String resource) throws IOException 
	{
		requestBody= given().spec(requestSpecificationGraphQL()).body(bodyJson.queryCharacterPayload(character_ID));
		
		//Reused method 
		
		user_call_with_http_request(resource,"POST");
		
		String actualName = getJsonPath(response, "data.character.name");
		String actualType = getJsonPath(response, "data.character.type");
		String actualStatus = getJsonPath(response, "data.character.status");
		
		assertEquals(actualName,expectedName);
		assertEquals(actualType,expectedType);
		assertEquals(actualStatus,expectedStatus);
	}
	
	@Given("Create Episode Payload with {string} {string} {string}")
	public void create_episode_payload_with(String episodeName, String air_date, String episode) throws IOException 
	{
		requestBody= given().spec(requestSpecificationGraphQL()).body(bodyJson.addEpisodeload(episodeName,air_date,episode));
	}
	
	@Then("the episodeID is created")
	public void the_episodeID_id_is_created() 
	{
		episode_ID = getJsonPath(response, "data.createEpisode.id");
	}
	
	@Then("verify episodeID created maps to {string} {string} using {string}")
	public void verify_episode_id_created_maps_to_using(String expectedEpisodeName, String airDate, String resource) throws IOException 
	{
		
		requestBody= given().spec(requestSpecificationGraphQL()).body(bodyJson.queryEpisodeIDPayload(episode_ID));
		
		//Reused method 
		
		user_call_with_http_request(resource,"POST");
		
		String actualName = getJsonPath(response, "data.episode.name");
		String actualAirDate = getJsonPath(response, "data.episode.air_date");
		
		assertEquals(actualName,expectedEpisodeName);
		assertEquals(actualAirDate,airDate);
	}
	
	@Given("Delete Location GraphQL Payload")
	public void delete_location_graph_ql_payload() throws IOException 
	{
		//{"query":"mutation {\n  deleteLocations(locationIds:[9144])\n  {\n    locationsDeleted\n  }\n}\n","variables":null}
		
		requestBody= given().spec(requestSpecificationGraphQL()).body(bodyJson.deleteLocationBody(location_id));
	}
	
	@Given("Delete Character GraphQL Payload")
	public void delete_character_graph_ql_payload() throws IOException 
	{
		requestBody= given().spec(requestSpecificationGraphQL()).body(bodyJson.deleteCharacterBody(character_ID));
	}
	
	@Given("Delete Episode GraphQL Payload")
	public void delete_episode_graph_ql_payload() throws IOException 
	{
		requestBody= given().spec(requestSpecificationGraphQL()).body(bodyJson.deleteEpisodeBody(episode_ID));
	}
	
	
}
