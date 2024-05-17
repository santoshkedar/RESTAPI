package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addPlaceBody;

public class TestDataBuild 
{
	public addPlaceBody addPlacePayload(String name, String language, String address)
	{
		List<String> listString= new ArrayList<String> ();
		listString.add("shoe park");
		listString.add("shop");
		
		addPlaceBody addPlaceJsonBody= new addPlaceBody();
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		addPlaceJsonBody.setLocation(location);
		addPlaceJsonBody.setAccuracy(50);
		addPlaceJsonBody.setName(name);
		addPlaceJsonBody.setPhone_number("(+91) 983 893 3937");
		addPlaceJsonBody.setAddress(address);
		addPlaceJsonBody.setTypes(listString);
		addPlaceJsonBody.setWebsite("http://google.com");
		addPlaceJsonBody.setLanguage(language);
		
		return addPlaceJsonBody;
		
	}
	
	public String deleteAPIBody(String place_id)
	{
		return "{\n"
				+ "\n"
				+ "    \"place_id\":\""+place_id+"\"\n"
				+ "}";
	}
	
	public String addLocationPayload(String locationName, String type, String dimension)
	{
		return "{\"query\":\"mutation\\n{\\n  createLocation(location: {name:\\\""+locationName+"\\\" type:\\\""+type+"\\\" dimension:\\\""+dimension+"\\\"} )\\n  {\\n    id\\n  }\\n  \\n}\\n\",\"variables\":null}";
	}

	public String addCharcterPayload(String characterName, String type, String status, String species, String gender,
			String image, String originID, String location_id) 
	{
		return "{\"query\":\"mutation {\\n  createCharacter(character: {name: \\\""+characterName+"\\\", type: \\\""+type+
				"\\\", status: \\\""+status+"\\\", species: \\\""+species+"\\\", gender: \\\""+gender+"\\\", image: \\\""+image+"\\\", "
				+ "originId: "+originID+", locationId: "+location_id+"}) {\\n    id\\n  }\\n}\\n\",\"variables\":null}";
	}

	public String addEpisodeload(String episodeName, String air_date, String episode) 
	{
		return "{\"query\":\"mutation {\\n  createEpisode(episode: {name: \\\""+episodeName+"\\\", air_date: \\\""+air_date+"\\\", episode: \\\""+episode+"\\\"}) "
				+ "{\\n    id\\n  }\\n}\\n\",\"variables\":null}";
	}

	public String queryLocationPayload(String location_id) 
	{
		// TODO Auto-generated method stub
		return "{\"query\":\"query\\n{\\n  location(locationId:"+location_id+")\\n  {\\n    name\\n    type\\n  }\\n}\\n\",\"variables\":null}";
	}

	public String queryEpisodeIDPayload(String episode_ID) {
		 
		return "{\"query\":\"query\\n{\\n  episode(episodeId:"+episode_ID+")\\n  {\\n    name\\n    air_date\\n  }\\n}\\n\",\"variables\":null}";
	}

	public String queryCharacterPayload(String character_ID) 
	{

		return "{\"query\":\"query\\n{\\n  character(characterId:"+character_ID+")\\n  {\\n    name\\n    type\\n    status\\n    species\\n    gender\\n  }"
				+ "\\n}\\n\",\"variables\":null}";
	}

	public String deleteLocationBody(String location_id) 
	{
		return "{\"query\":\"mutation {\\n  deleteLocations(locationIds:["+location_id+"])\\n  {\\n    locationsDeleted\\n  }\\n}\\n\",\"variables\":null}";
	}

	public String deleteCharacterBody(String character_ID) 
	{
		return "{\"query\":\"mutation {\\n \\n  deleteCharacters(characterIds:["+character_ID+"])\\n  {\\n    charactersDeleted\\n  }\\n}\\n\",\"variables\":null}";
	}

	public String deleteEpisodeBody(String episode_ID) 
	{
		return "{\"query\":\"mutation {\\n \\ndeleteEpisodes(episodeIds:["+episode_ID+"])\\n  {\\n    episodesDeleted\\n  }\\n}\\n\",\"variables\":null}";
	}
	
}

