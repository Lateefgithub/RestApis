package YT_RestAssuredTests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Test4PostRequest {

	@Test
	public void testPostReq(){
		given().
			headers("AppKey","Key-value").
			param("wfsfirst_name","first").
			param("wfslast_name","last").
			param("wfsemail","test@test.com").
		when().
			post("http://api.fonts.com/rest/json/Accounts/").
		then().
		statusCode(401).log().all();
	}
}
