package stepDefinations;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification req;
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data  = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		
		res=given().spec(requestspecifications()).body(data.AddPlaceload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
				{
					response = res.when().post(resourceAPI.getResource()).
							then().spec(resspec).extract().response();
				}
		else if (method.equalsIgnoreCase("GET"))
				{
			response = res.when().get(resourceAPI.getResource()).
					then().spec(resspec).extract().response();
				}

	}

	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
		System.out.println(response);
		int statucode = response.getStatusCode();
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String Expectedvalue) {
	    assertEquals(getJsonPath(response, keyvalue) ,Expectedvalue);
	}

	@Then("verify Place_id created maps to {string} using {string}")
	public void verify_Place_id_created_maps_to_using(String expectedval, String resource) throws IOException {
	    
		String place_id = getJsonPath(response, "place_id");
		res=given().spec(requestspecifications()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resource, "GET");
		String actualval = getJsonPath(response, "name");
		assertEquals(actualval, expectedval);

	}

}
