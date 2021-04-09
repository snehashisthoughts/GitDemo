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

public class Utils {
	public static RequestSpecification req;
	public RequestSpecification requestspecifications() throws IOException
	{
		if(req==null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req =new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log ))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\Learning\\Udemy learning\\Eclipse Project\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response Response, String key) 
	{
	   String resp = Response.asString();
		JsonPath js = new JsonPath(resp);
	    return js.get(key).toString();
	}
}
