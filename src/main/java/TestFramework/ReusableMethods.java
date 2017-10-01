package TestFramework;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	/*static Properties prop = new Properties();
	
	public void getData() throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\Lateef\\workspace\\DemoRestAssuredProject\\src\\files\\env.properties");
		prop.load(fis);
	}*/
	
		
	public static XmlPath rawToXML(Response r){
		String respon = r.asString();
		XmlPath x=new XmlPath(respon);
		return x;
	}
	
	
	public static JsonPath rawToJson(Response r){
		String respon = r.asString();
		JsonPath x=new JsonPath(respon);
		return x;
	}
	
	public static String getSessionKEY(){
		
			//Creating Session
			RestAssured.baseURI="http://localhost:8080";
			Response res=given().header("Content-Type","application/json").
			body("{\"username\": \"abdulsalam12000\", \"password\": \"Usual12000\"}").
			when().
			post("/rest/auth/1/session").
			then().statusCode(200).
			extract().response();
			JsonPath js=ReusableMethods.rawToJson(res);
					String sessionid=js.getString("session.value");
			return sessionid;
		
	}
	
	public static int getid()
	{
		//Creating a new Issue (defect)
		RestAssured.baseURI="http://localhost:8080";
		Response res=given().header("Content-Type","application/json").
		header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKEY()).
		body("{"+
		    "\"fields\": {"+
		        "\"project\": {"+
		            "\"key\": \"LAT\""+
		        "},"+
		        "\"summary\": \"Testing POstmandefect with REST Assured API automation\","+

		        "\"issuetype\": {"+
		        	    "\"name\":\"Bug\""+
		        "}"+
		    
		    "}}").when().
		post("/rest/api/2/issue").then().statusCode(201).extract().response();
		JsonPath js=ReusableMethods.rawToJson(res);
		String Sid=js.getString("id");
		int id= Integer.parseInt(Sid);
		System.out.println(id);
		return id;

	}
	
	public static int getCommentid()
	{
		//Adding comment in Jira Defect with reusable getid method
		RestAssured.baseURI="http://localhost:8080";
		Response res=given().header("Content-Type","application/json").
		header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKEY()).
		body("{"+
		    "\"body\": \"I am adding a comment to this defect using api REST Assured autotmation\","+
		    "\"visibility\": {"+
		        "\"type\": \"role\","+
		        "\"value\": \"Administrators\""+
		    "}}").when().
		post("/rest/api/2/issue/"+ReusableMethods.getid()+"/comment").then().statusCode(201).extract().response();
		
		JsonPath js=ReusableMethods.rawToJson(res);
		String commentid = js.getString("id");
		int c_id = Integer.parseInt(commentid);
		return(c_id);

//System.out.println(ReusableMethods.getid());
	}
}
