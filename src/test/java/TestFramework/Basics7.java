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


public class Basics7 {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\Lateef\\workspace\\DemoRestAssuredProject\\src\\files\\env.properties");
		prop.load(fis);

		
	}
	@Test
	public void JiraAPICreateComment()
	{
		//Adding comments to Jira Defect with reusable getid method
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
		Response res=given().header("Content-Type","application/json").
		header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKEY()).
		body("{"+
		    "\"body\": \"10117 Bombom comment Testing adding comment to this defect using api33 3  3  3  3\","+
		    "\"visibility\": {"+
		        "\"type\": \"role\","+
		        "\"value\": \"Administrators\""+
		    "}}").when().
		post("/rest/api/2/issue/"+ReusableMethods.getid()+"/comment").then().assertThat().statusCode(201).extract().response();

//System.out.println(ReusableMethods.getid());
	}
	
}
