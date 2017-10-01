package YT_RestAssuredTests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;
import java.io.InputStream;

public class Test5_ReadResponseInDiffWays {
	/*
	 * To get all response as string
	 */
	
	//@Test
	public void testGetResponseAsString(){
		String responseAsString = get("http://services.groupkt.com/country/search?text=lands").asString();
		System.out.println("My Response:\n\n\n"+responseAsString);
	}
	
	

		/*
		 * To get all response as InputStream
		 * @throws IOException
		 */
		
		//@Test
		public void testGetResponseAsInputStream() throws IOException{
			InputStream stream= get("http://services.groupkt.com/country/get/iso2code/cn").asInputStream();
			System.out.println("Stream Length: "+stream.toString().length());
			stream.close();
		}
		
		
		/*
		 * To get all response as ByteArray
		 */
		
		//@Test
		public void testGetResponseAsByteArray(){
			byte[] byteArray = get("http://services.groupkt.com/country/get/iso2code/cn").asByteArray();
			System.out.println(byteArray.length);
		}
		
		/*
		 * To get all response using path
		 */
		
		//@Test
		public void testExtraxtDetailsUsingPath(){
			String href=
					when().
						get("http://jsonplaceholder.typicode.com/photos/1").
					then().
						contentType(ContentType.JSON).
						body("albumId",equalTo(1)).
					extract().
						path("url");
						
			
			System.out.println(href);
			
			when().get(href).then().statusCode(200);
						
		}
		
		
		/*
		 * Extract details using the path in one line
		 */
		
		//@Test
		public void testExtraxtPathInOneLine(){
			//type1:
			String href1= get("http://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
			System.out.println("Fetched URL 1: " +href1);
			when().get(href1).then().statusCode(200);
			
			//type2
			String href2= get("http://jsonplaceholder.typicode.com/photos/1").andReturn().jsonPath().getString("thumbnailUrl");
			System.out.println("Fetched URL 2: "  +href2);
			when().get(href2).then().statusCode(200);
						
		}
		
		
		
		/*
		 * Extract details as Response for further use
		 */
		
		@Test
		public void testExtractDetailsUsingResponse(){
			Response response=
			when().
				get("http://jsonplaceholder.typicode.com/photos/1").
			then().
			extract().
				response();
			
			System.out.println("Content Type: " +response.contentType());
			System.out.println("Href: " +response.path("url"));
			System.out.println("Status code: " +response.statusCode());
			//response.
						
		}
		

}
