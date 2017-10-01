package TestFramework;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class Tenapitestd {
	
	
	@Test
	public void getUKLocationAPI()
 {
		// TODO Auto-generated method stub
		//Base URL/Host
		//RestAssured.baseURI=prop.getProperty("UKLOCATIONENDPOINT");
			given().get("http://www.webservicex.net/uklocation.asmx/GetUKLocationByCounty?County=kent").
			//param("County","Kent").
			//when().
			//get(Resources.getUkLocationByCountyData()).
			//then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().
			then().
			body("NewDataSet.Table.Town", equalTo("Cliffe","Cliffe Woods","Cliffs End"));
		//	body(hasXPath("/NewDataSet/Table/Town[text()='Cliffe']"));
	
 

	}

	private ResponseAwareMatcher<Response> equalTo(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}

}
