package YT_RestAssuredTests;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.*;


public class Test_RestABasicFeatures {

	
	/**
	 * 
	 * Simply checking status
	 */
	//@Test
	public void testStatuscode(){
		given().
			get("http://jsonplaceholder.typical.com/post/3").
		then().statusCode(200);
	}
	
	
	//@Test(description= "how to log all results")
	public void testlogging(){
		given().
			get("http://www.webservicex.net/uklocation.asmx/GetUKLocationByCounty?County=kent").
		then().
			statusCode(200).
			log().all();
	}
	
	
	/**
	 * 
	 * This test fails because the format of the output needs working on
	 */
	//@Test(description= "Verifying multiple content using org.hamcrest.Matchers library")
	public void testHasItemFunction(){
		given().
			get("http://www.webservicex.net/uklocation.asmx/GetUKLocationByCounty?County=kent").
		then().
			body("NewDataSet.Table",hasItems("Cliffe","Cliffe Woods","Cliffs End"));
		
	}
	
	//@Test(description= "how to log all results")
	public void testlogging2(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/in").
		then().
			statusCode(200).
			log().all();
	}
	
	//@Test(description= "Verifying single content using org.hamcrest.Matchers library's equalTo method")
	public void testEqualToFunction(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/us").
		then().
			body("RestResponse.result.name",equalTo("United States of America"));
	}
	
	//@Test(description= "Verifying multiple content using org.hamcrest.Matchers library")
	public void testHasItemFunction2(){
		given().
			get("http://services.groupkt.com/country/get/all").
		then().
			body("RestResponse.result.name",hasItems("Afghanistan","Algeria","Albania","Andorra", "American Samoa"));
	}
	
	
	//@Test(description= "Parameters and Headers can be set")
	public void testParametersAndHeaders(){
		given().
			param("Key1", "value1").
			header("headA", "valueA").
		when().
			get("http://services.groupkt.com/country/get/iso2code/us").
		then().
			statusCode(200).
			log().all();
	}
	
	/**
	 * using 'and' to increase readability
	 * generally used when writing in one line xpath style
	 * Note that removing the 'and() does not make any difference
	 */
	//@Test()
	public void testAndFeatureForReadability(){
		given().param("Key1", "value1").and().header("headA", "valueA").
		when().
			get("http://services.groupkt.com/country/get/iso2code/cn").
		then().
			assertThat().statusCode(200).and().body("RestResponse.result.alpha3_code",equalTo("CHN"));
	}
	
	
}
