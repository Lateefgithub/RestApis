package YT_RestAssuredTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.xml.HasXPath.*;

import org.testng.annotations.Test;



public class Test_1BasicFeaturesForXml {
	
/**
 * To test xml response for single body content
 */
	//@Test
	public void testSingleContent(){
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
		then().
			body("CUSTOMER.ID", equalTo("10")).
			log().all();
	}
	
	/**
	 * To test xml response for multiple body content
	 * test can also be written in xpath style i.e. in one line but that may be unreadable
	 * Note the use of 'and()' in some lines - note mandatory
	 */
		//@Test
		public void testMultipleContent(){
			given().
				get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
			then().
				body("CUSTOMER.ID", equalTo("10")).and().body("CUSTOMER.FIRSTNAME", equalTo("Sue")).and().
				body("CUSTOMER.LASTNAME", equalTo("Fuller")).and().
				body("CUSTOMER.STREET", equalTo("135 Upland Pl.")).
				body("CUSTOMER.CITY", equalTo("Dallas")).and().
				log().all();
		}
		
		/**
		 * Compare complete text in one go
		 */
			//@Test
			public void testCompleteTextInOneGo(){
				given().
					get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
				then().
					body("CUSTOMER.text()", equalTo("10SueFuller135 Upland Pl.Dallas")).
					log().all();
			}
			
			/**
			 * xpath can also be used to find values
			 * Note the '/' before the first tag name
			 */
			//@Test
			public void testUsingXpath1(){
				given().
					get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
				then().
					body(hasXPath("/CUSTOMER/FIRSTNAME"), containsString("Sue")).
					log().all();
			}
			
			/**
			 * xpath types
			 */
			@Test
			public void testUsingXpath2(){
				given().
					get("http://www.thomas-bayer.com/sqlrest/INVOICE/").
				then().
					body(hasXPath("/INVOICEList/INVOICE[text()='40']")).and().
					log().all();
			}

}
