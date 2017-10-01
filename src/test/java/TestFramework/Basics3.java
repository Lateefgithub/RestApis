package TestFramework;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import TestFramework.Resources;
import TestFramework.Payload;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Basics3 {
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\Lateef\\workspace\\DemoRestAssuredProject\\src\\files\\env.properties");
		prop.load(fis);

		
	}

	@Test
	public void AddandDeletePlace(){
		


		//Task 1- Grab the response
		RestAssured.baseURI=prop.getProperty("HOST");
		
		Response res = given().
		queryParam("key",prop.getProperty("KEY")).
		body(Payload.getPostData()).
		when().
		post(Resources.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).
		extract().response();
		
		String responseString=res.asString();
		System.out.println(responseString);
		
		
		//Task2- Grab the Place ID from response
		
		JsonPath js=new JsonPath(responseString);
		String placeid=js.get("place_id");
		System.out.println(placeid);
		
		//Task3- Place this placeid in the Delete request
		given().
		queryParam("key","AIzaSyBpf6vEcxijggrreYzzHDcKbxrny0fzg3g").
		body("{"+
			  "\"place_id\": \""+placeid+"\""+
		"}").
		when().
		post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		
	}
}