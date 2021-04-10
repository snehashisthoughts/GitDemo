package basic_rest1;

import org.testng.annotations.Test;

import file.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	public static void main(String[] args) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json").body(Payload.AddBook("sneh103","muk103")).when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
	String resp_id = js.getString("ID");
		System.out.println("ID of the added book : "+resp_id);
	}



}
