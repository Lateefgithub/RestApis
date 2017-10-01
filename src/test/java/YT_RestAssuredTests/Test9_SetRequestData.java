package YT_RestAssuredTests;

import org.testng.annotations.Test;

import groovyjarjarantlr.collections.List;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

/**
 * This class is to set different type of data in request call
 * @author Lateef
 *
 */
public class Test9_SetRequestData {
	/**
	 * Generally CONNECT is used with HTTPS requests
	 *  
	 *  refer: https://stackoverflow.com/questions/11697943/when-should-one-use-connect-and-get-http-methods-at-http-proxy-server
	 */
	//@Test
	public void testConnectRequest(){
		when().
			request("CONNECT", "https://api.fonts.com/rest/json/Accounts/").
		then().
			statusCode(400);
	}
	
	/**
	 *  
	 * in GET requests we can set query parameters
	 */
	//@Test
	public void testQueryParameters(){
		given().
			queryParam("A", "A val").
			queryParam("B", "B val").
		when().
			get("https://api.fonts.com/rest/json/Accounts").
		then().
			statusCode(400);
	}
	
	/**
	 *  
	 * in POST requests we can set form parameters
	 */
	//@Test
	public void testFormParameters(){
		given().
			formParam("A", "A val").
			formParam("B", "B val").
		when().
			post("https://api.fonts.com/rest/json/Accounts").
		then().
			statusCode(400);
	}
	
	/**
	 *  To set parameters - recommended way
	 *  If request is GET then param will be treated as QueryParameter
	 *  If request is POST param will be treated as FormParameter
	 */
	//@Test
	public void testSetParameters(){
		given().
			param("A", "A val").
			param("B", "B val").
		when().
			post("https://api.fonts.com/rest/json/Accounts").
		then().
			statusCode(400);
	}
	
	/**
	 *  To set multiple value parameters
	 *  We can pass list, multiple values or no values in param
	 */
	@Test
	public void testSetMultiValueParameters(){
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		
		given().
			param("A","val1", "val2", "val3").
			param("B").
			param("C", list).
		when().
			post("https://api.fonts.com/rest/json/Accounts").
		then().
			statusCode(400);
	}

}
