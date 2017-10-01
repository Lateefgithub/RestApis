package TestFramework;
//Latest


import io.restassured.RestAssured;
import static io.restassured.RestAssured.get;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.xml.HasXPath.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//import TestFramework.ReusableMethods;
import files.Resources;
import files.ReusableMethods;

public class Tenapitestc {

	Properties prop = new Properties();
	
	//@BeforeTest
	public void getData() throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\Lateef\\workspace\\DemoRestAssuredProject\\src\\files\\env.properties");
		prop.load(fis);

		
	}
	
	@Test
	public void getUKLocationAPI()
 {
		
		Response resp=
				given().get("http://www.webservicex.net/uklocation.asmx/GetUKLocationByCounty?County=kent").
			
				then().assertThat().statusCode(200).and().contentType(ContentType.XML).extract().response();
			//then();
			//body("NewDataSet.Table.Town", equalTo("Cliffe","Cliffe Woods","Cliffs End"));
		//	body(hasXPath("/NewDataSet/Table/Town[text()='Cliffe']"));
	
			String respon = resp.asString();
			XmlPath xm=new XmlPath(respon);
			//System.out.println(xm);
			//System.out.println(xm.getString("/NewDataSet/Table/Town[text()]"));
			String backToXML = xm.getString("/NewDataSet/Table/Town[text()]");
			//System.out.println(backToXML);
			
			//to continue from here
			//System.out.println(get(backToXML).then().body(hasXPath("NewDataSet.Table.Town"hasItem("Cliffe")));

	}


}