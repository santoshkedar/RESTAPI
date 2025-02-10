package stepDefinations;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks 
{
	
/*	@After("@AddPlace")
	public void AfterScenario() throws IOException
	{
		StepDefination s = new StepDefination();
		if(StepDefination.place_id.get(0)!=null)
		{
			s.delete_place_api_payload();
		}
	}*/
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//This code execute only when place_id is null i.e. DeletePlace API is called without Add place API
		//And Fetch place_id which will be passed to DeletePlace API
		
		StepDefination s = new StepDefination();
		if(StepDefination.place_id.get(0)==null)
		{
			s.add_place_payload_with("Smita", "Hindi", "1812 Santa dr");
			s.user_call_with_http_request("AddPlaceAPI", "POST");
			s.verify_place_id_created_maps_to_using("Smita", "getPlaceAPI");
		}
	}
	
}
