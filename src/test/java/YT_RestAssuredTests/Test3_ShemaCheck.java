package YT_RestAssuredTests;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class Test3_ShemaCheck {
	
	/**
	 * Verify response type
	 */
	public void testContent(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/cn").
		then().
			statusCode(200).
			contentType(ContentType.JSON);
			//ContentType(ContentType.HTML);
			//ContentType(ContentType.XML);
	}

}
