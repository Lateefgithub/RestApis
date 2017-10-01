package YT_RestAssuredTests;

import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Map;

import org.testng.annotations.Test;

public class Test8_GetHeadersAndCookies {
	
	/**
	 * To get response headers
	 * Note the use of the enhanced for loop in under - to get all heaers
	 */
	
	//@Test
	public void testResponseHeaders(){
		Response response = get("http://jsonplaceholder.typicode.com/photos/");
		// To get single header
		String headerCFRAY = response.getHeader("CF-RAY");
		System.out.println(">>>>>>> Header: " +headerCFRAY);
		
		//to get all headers
		Headers headers = response.getHeaders();
		for (Header h: headers){
			System.out.println(h.getName()+" : "+h.getValue());
			
		}
		
	}
	
	
	/**
	 * To get cookies
	 * 
	 */
	//@Test
	public void testCookies(){
		Response  response = get("http://jsonplaceholder.typicode.com/photos");
		Map<String, String> cookies = response.getCookies();
		for(Map.Entry<String, String> entry: cookies.entrySet()){
			System.out.println(entry.getKey() +" : "+entry.getValue());
		}
	}
	
	
	/**
	 * To get cookies
	 * 
	 */
	@Test
	public void testDetailedCookies(){
		Response response = get("http://jsonplaceholder.typicode.com/photos");
		
		Cookie a= response.getDetailedCookie("__cfduid");
		System.out.println("Detailed: " +a.hasExpiryDate());
		System.out.println("Detailed: " +a.getExpiryDate());
		System.out.println("Detailed: " +a.hasValue());
		System.out.println("Domain: " +a.getDomain());
		System.out.println("Detailed: " +a.hasPath());





			
		
	}
	

}
