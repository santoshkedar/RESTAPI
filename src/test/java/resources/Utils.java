package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils 
{
	public static RequestSpecification req;
	public static RequestSpecification reqCreateLocation;
	
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req= new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}
	
	public RequestSpecification requestSpecificationCreateLocation() throws IOException
	{
		if(reqCreateLocation==null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			reqCreateLocation= new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return reqCreateLocation;
		}
		return reqCreateLocation;
	}
	
	public RequestSpecification requestSpecificationGraphQL() throws IOException
	{
		if(reqCreateLocation==null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("GraphQLlogging.txt"));
			reqCreateLocation= new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return reqCreateLocation;
		}
		return reqCreateLocation;
	}
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("//Users//santoshkedar//work//RestAPI//APIFramework//src//test//java//resources//global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	// This method return value from response for the key provided 
	
	public String getJsonPath(Response response, String key)
	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
