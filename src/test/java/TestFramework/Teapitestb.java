
	
	package TestFramework;



	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.path.xml.XmlPath;
	import io.restassured.response.Response;

	import static io.restassured.RestAssured.given;


	//import TestFramework.ReusableMethods;


public class Teapitestb {
	public void retrieve_uk_location(String fromcounty, String countyToCheck){
				RestAssured.baseURI="http://www.webservicex.net";
				Response resp= given().
				param("County",fromcounty).
				when().
				get(getUkLocationByCountyData()).
				then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().
				extract().response();
				//and().body("NewDataSet.Table.Town", equalTo("Cliffe"));
				
				String respon = resp.asString();
				//System.out.println(respon);
				XmlPath xm=new XmlPath(respon);
				String rcounty = xm.getString("NewDataSet.Table.County");
				//System.out.println(xm);
				if (rcounty ==countyToCheck){
					System.out.println("TEST PASS " + rcounty);
									
					} else{
				
					System.out.println("TEST FAIL"); 

					}	
	}

	
	public static String getUkLocationByCountyData(){
		String rget="/uklocation.asmx/GetUKLocationByCounty";
		return rget;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Teapitestb TenTen = new Teapitestb();
		TenTen.retrieve_uk_location("Kent", "Kent");
	}
}
