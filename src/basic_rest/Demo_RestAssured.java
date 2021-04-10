package basic_rest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import file.Payload;

public class Demo_RestAssured {

	public static void main(String[] args) throws IOException {

	RestAssured.baseURI = "https://rahulshettyacademy.com";
	//Add API example
	String Response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(new String(Files.readAllBytes(Paths.get("D:\\Learning\\Udemy learning\\Eclipse Project\\DemoProj_RestAssured\\Repository\\AddPlace.json")))).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	System.out.println(Response);
	JsonPath js = new JsonPath(Response); //for parsing the Json response
	String placeid = js.getString("place_id");
	System.out.println(placeid);
	
	//Update API example
		
	given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\r\n" + 
			"\"place_id\":\""+ placeid +"\",\r\n" + 
			"\"address\":\"70 Summer walk, USA\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	System.out.println("This is from git demo version");

	}

}
