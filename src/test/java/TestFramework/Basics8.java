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


public class Basics8 {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\Lateef\\workspace\\DemoRestAssuredProject\\src\\files\\env.properties");
		prop.load(fis);

		
	}
	@Test
	public void JiraAPICommentUpdate()
	{
		//final int DEFECTID = ReusableMethods.getid();
		//Updating comment in Jira Defect with reusable getid method
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
		Response res=given().header("Content-Type","application/json").
		header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKEY()).
		pathParams("commentid","10117").
		body("{"+
		    "\"body\": \"Hey I am updating existing comment to this defect using api REST Assured autotmation\","+
		    "\"visibility\": {"+
		        "\"type\": \"role\","+
		        "\"value\": \"Administrators\""+
		    "}}").
		when().
		put("/rest/api/2/issue/10132/comment/{commentid}").then().statusCode(200).extract().response();
		
		//JsonPath js=ReusableMethods.rawToJson(res);
		//String id = js.getString("id");
		//System.out.println(id);

//System.out.println(ReusableMethods.getid());
	}
	
}