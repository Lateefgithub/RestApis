package YT_RestAssuredTests;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;

import java.util.List;

public class Test7_JsonPath {
	
	/**
	 * Extract details as String and fetching further details w/o using json path
	 */
	
	//@Test
	public void testJsonPath1(){
		String responseAsString =
				when().
					get("http://jsonplaceholder.typicode.com/photos/").
				then().
				extract().asString();
		List<Integer> albumIds = from(responseAsString).get("id");
		System.out.println(albumIds);
		
		System.out.println("size of albumIds is :" +albumIds.size());
	}
	
	
	/**
	 * Extract details as String and fetching further details w/o using json path
	 */
	
	@Test
	public void testJsonPath2(){
		String responseAsString =
				when().
					get("http://services.groupkt.com/country/get/all").
				then().
				extract().asString();
		
		JsonPath jsonpath = new JsonPath(responseAsString);
		List<String> list = jsonpath.get("RestResponse.result.name");
		System.out.println(list);
		System.out.println("Size of names is " +list.size());
	}

}
