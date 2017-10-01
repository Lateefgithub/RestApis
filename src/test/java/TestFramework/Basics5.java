package TestFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestFramework.Resources;

import TestFramework.ReusableMethods;

public class Basics5 {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\Lateef\\workspace\\DemoRestAssuredProject\\src\\files\\env.properties");
		prop.load(fis);

		
	}
	
	@Test
	public void extractingNamesAPI()
 {
		// TODO Auto-generated method stub
		//Base URL/Host
		RestAssured.baseURI=prop.getProperty("HOST");
		
		Response res=given().
			param("location","-33.8670522,151.1957362").
			param("radius","500").
			param("key","AIzaSyDgvC69cPZ5xGFytV49wWdAZzGmT2uSkKA").
			when().
			get(Resources.placeSearchData()).
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			//body("results[0].geometry.location.lat",equalTo("-33.8688197"));
			body("results[0].name",equalTo("Sydney")).and().
			body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
			header("Server", "pablo").
			extract().response();
		
		JsonPath js=ReusableMethods.rawToJson(res);
		
		int count = js.get("results.size()");
		System.out.println(count);
	
		for (int i=0;i<count;i++)
		{
			System.out.println(i);
			System.out.println(js.getString(("results["+i+"].name"))); //Note the way i is handled in order to take it as a variable 
			
		}
	
		
		
		

	}

}
