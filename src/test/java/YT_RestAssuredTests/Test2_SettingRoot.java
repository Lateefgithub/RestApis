package YT_RestAssuredTests;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.xml.HasXPath.*;
import org.testng.annotations.Test;

public class Test2_SettingRoot {
	
	/**
	 * Basic way to test all parameters
	 */
	//@Test
	public void testWithoutRoot(){
		given().
			get("http://services.groupkt.com/country/get/iso3code/ita").
		then().
			body("RestResponse.result.name", is("Italy")).and().
			body("RestResponse.result.alpha2_code", is("IT")).and().
			body("RestResponse.result.alpha3_code", is("ITA"));
		
		}
	

	/**
	 * Recommended way to test all parameters using the root feature
	 */
	//@Test
	public void testWithRoot(){
		given().
			get("http://services.groupkt.com/country/get/iso3code/ita").
		then().
			root("RestResponse.result").
			body("name", is("Italy")).and().
			body("alpha2_code", is("IT")).and().
			body("alpha3_code", is("ITA"));			
	}

	/**
	 * We can detach root path in between
	 */
	@Test
	public void testDetachRoot(){
		given().
			get("http://services.groupkt.com/country/get/iso3code/ita").
		then().
			root("RestResponse.result").
			body("name", is("Italy")).
			body("alpha2_code", is("IT")).
			detachRoot("result").
			body("result.alpha3_code", is("ITA"));			
	}

}
