package TestFramework;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Basics4 {

	@Test
	public void postDataXML() throws IOException{
		
		String postdata=GenerateStringFromResource("C:\\Users\\Lateef\\Documents\\Lateef\\General\\UDEMY\\RestAssured\\postdata.xml");
		
		RestAssured.baseURI="https://maps.googleapis.com";
		
		Response resp=given().
		queryParam("key","AIzaSyBpf6vEcxijggrreYzzHDcKbxrny0fzg3g").
		body(postdata).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).
		extract().response();
		
		String respon = resp.asString();
		System.out.println(respon);
		
		
		
	}
	
	//Method to convert the xml into string..
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	
}
