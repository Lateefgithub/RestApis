package YT_RestAssuredTests;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static io.restassured.path.json.JsonPath.from;
//import static io.restassured.path.xml.XmlPath.from;
import java.util.List;

import org.testng.annotations.Test;


public class Test6_GroovyFeatures {
	/**
	 * Verify is some expected name present in  response or not
	 */
	//@Test
	public void testPresenceOfElements(){
		given().
			get("http://services.groupkt.com/country/search?text=lands").
		then().
			body("RestResponse.result.name", hasItems("Cayman Islands","Cook Islands","Falkland Islands (Malvinas)")).log().all();
		
	}
	
	/**
	 * ResAssured implemented in Groovy and hence Groovy advantages can be taken
	 * 
	 * Here we are adding lenght of all "alpha3_code" coming in the response
	 */
	//@Test
	public void testLengthOfResponse(){
		when().
			get("http://services.groupkt.com/country/search?text=islands").
		then().
			body("RestResponse.result.alpha3_code*.length().sum()", greaterThan(40));
		}
	
	/**
	 * To get all attributes as a list
	 * Also note the use of enhanced for loop
	 */
	
	//@Test
	public void testGetResponseAsList(){
		String response = get("http://services.groupkt.com/country/search?text=lands").asString();
		List<String> ls = from(response).getList("RestResponse.result.name");
		
		System.out.println("ListSize: " + ls.size());
		for(String country: ls){ //enhanced for loop
			if(country.equals("Solomon Islands"))
				System.out.println("Found Island");
		
		}
		
	}
	/*
	 * This does not work because of the soapcall probably 
	 * 
	
		@Test
		public void testGetResponseAsListxml(){
			String response = get("http://www.webservicex.net/uklocation.asmx/GetUKLocationByCounty?County=kent").asString();
			List<String> ls = from(response).getList("NewDataSet.Table.Town");
			
			System.out.println("ListSize: " + ls.size());
			for(String country: ls){ //enhanced for loop
				if(country.equals("Cobham"))
					System.out.println("Found Cobham");
			
			}

			
		}
 */
		
	/**
	 * To get response as List and apply some conditions on it
	 * The groovy has an implicit variable called 'it' which represents the current item in the list
	 */
		@Test
		public void testConditionsOnList(){
			String response = get("http://services.groupkt.com/country/search?text=lands").asString();
			List<String> ls = from(response).getList("RestResponse.result.findAll{it.name.length()>30}.name");
			
			System.out.println(ls);//[South Georgia and the South Sandwich Islands]
			
		}

}


