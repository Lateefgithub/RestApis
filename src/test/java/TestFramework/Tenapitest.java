package TestFramework;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hamcrest.Matcher;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//import TestFramework.ReusableMethods;
import files.Resources;
import files.ReusableMethods;

public class Tenapitest {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\Lateef\\workspace\\DemoRestAssuredProject\\src\\files\\env.properties");
		prop.load(fis);

		
	}
	
	@Test
	public void getUKLocationAPI()
 {
		// TODO Auto-generated method stub
		//Base URL/Host
		RestAssured.baseURI=prop.getProperty("UKLOCATIONENDPOINT");
		Response resp=(Response) given().
			param("County","Kent").
			when().
			get(Resources.getUkLocationByCountyData()).
			then().assertThat().statusCode(200).and().contentType(ContentType.XML);
			//then().assertThat().statusCode(200).and().
			//body("NewDataSet.Table.Town", equalTo("Cliffe"));
		//	body(hasXPath("/NewDataSet/Table/Town[text()='Cliffe']"));
			
		String respon = resp.asString();
		//System.out.println(respon);
		XmlPath xm=new XmlPath(respon);
		System.out.println(xm.getString("/NewDataSet/Table/Town[text()]"));
		System.out.println(xm.getList("NewDataSet.Table.Town"));

		
	/*		
		String respon = resp.asString();
		//System.out.println(respon);
		XmlPath xm=new XmlPath(respon);
		System.out.println(xm.getString("/NewDataSet/Table/Town[text()]"));
		assertThat()
	*/
		//File file = new File (respon);
		//String Postcode = xm.getString("**.find { it.name == 'Cliffe' }.PostCode");
		//System.out.print (Postcode);
		/* try{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		  Document doc = builder.parse(file);
		  NodeList nodes = doc.getElementsByTagName("Table");
		  Element element =(Element)nodes.item(1);
		  NodeList Town = element.getElementsByTagName("Town");
		  Element line = (Element)Town.item(1);
		  System.out.println("Town name: " + line.getFirstChild().getTextContent());
			try{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		  Document doc = builder.parse(file);
		  NodeList nodes = doc.getElementsByTagName("Table");
	 
		  for (int i=0; i<nodes.getLength(); i++){
			  Element element =(Element)nodes.item(i);
			  NodeList Table = element.getElementsByTagName("Town");
			  Element line = (Element)Table.item(0);
			  System.out.println("Town name: " + line.getFirstChild().getTextContent());
			  NodeList Town = element.getElementsByTagName("County");
			  line = (Element)Table.item(0);
			  System.out.println("County name: " + line.getFirstChild().getTextContent());
			  NodeList PostCode = element.getElementsByTagName("PostCode");
			  line = (Element)Table.item(0);
			  System.out.println("PostCode name: " + line.getFirstChild().getTextContent()); 
			  System.out.println();
			  
		  }
				
		}
		catch (Exception e){
			e.printStackTrace();
		}/* 
/*
 * XmlPath xm=ReusableMethods.rawToXML(resp);
		String tname=xm.getString("Town");
		System.out.println(tname);
		
		*/
		
 

	}

/*	private Matcher<?> hasXPath(String string) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}