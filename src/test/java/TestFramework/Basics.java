package TestFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestFramework.Resources;

public class Basics {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\Lateef\\workspace\\DemoRestAssuredProject\\src\\files\\env.properties");
		prop.load(fis);

		
	}
	
	@Test
	public void getPlaceAPI()
 {
		// TODO Auto-generated method stub
		//Base URL/Host
		RestAssured.baseURI=prop.getProperty("HOST");
		
		given().
			param("location","-33.8670522,151.1957362").
			param("radius","500").
			param("key","AIzaSyDgvC69cPZ5xGFytV49wWdAZzGmT2uSkKA").
			when().
			get(Resources.placeSearchData()).
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			//body("results[0].geometry.location.lat",equalTo("-33.8688197"));
			body("results[0].name",equalTo("Sydney")).and().
			body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
			header("Server", "pablo").and().header("Vary", equalTo("Accept-Language"));
		

	}

}
