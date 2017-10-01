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

//Reusable issue created
public class Basics6a {

	static Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\Lateef\\workspace\\DemoRestAssuredProject\\src\\files\\env.properties");
		prop.load(fis);

		
	}
	@Test
	public static int JiraAPI()
	{
		//Creating a new Issue (defect)
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
		Response res=given().header("Content-Type","application/json").
		header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKEY()).
		body("{"+
		    "\"fields\": {"+
		        "\"project\": {"+
		            "\"key\": \"LAT\""+
		        "},"+
		        "\"summary\": \"Testing POstmandefect with REST Assured API\","+

		        "\"issuetype\": {"+
		        	    "\"name\":\"Bug\""+
		        "}"+
		    
		    "}}").when().
		post("/rest/api/2/issue").then().statusCode(201).extract().response();
		JsonPath js=ReusableMethods.rawToJson(res);
		int id=js.get("id");
		return id;
		//System.out.println("id is :" + id);

	}
	
}