package com.demo.test;

import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import static com.demo.utils.Formatter.jsonPathConverter;



import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LocalAPITest {

	@BeforeTest
	public void setUp(){
		baseURI="http://localhost:3000";

	}
	@Test(priority = 0)
	public void getSubjects() {

		Response response = given().header("Content-Type","Application.json")
				.contentType(ContentType.JSON).accept(ContentType.JSON)
				.when().get("Subjects")
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id[0]"), 7);
		Assert.assertEquals(response.jsonPath().getString("Topic[0]"), "Java");
	}


	@Test(priority = 1)
	public void post() {


		String firstName ="Puma";
		String lastName= "Ragu";

		Response response = given().header("Content-Type","Application.json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request(firstName,lastName))
				.when().post("users")
				.then().extract().response();
		JsonPath jsonPath = jsonPathConverter(response);
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertNotNull(response.body());
		Assert.assertEquals(jsonPath.getString("firstName"), firstName);
		Assert.assertEquals(jsonPath.getString("lastName"), lastName);
	}

	@Test(priority = 2)
	public void put() {

		JSONObject request= new JSONObject();

		String firstName ="Amala";
		String lastName= "Sharma";

		Response response = given().header("Content-Type","Application.json").contentType(ContentType.JSON).accept(ContentType.JSON).
				body(request(firstName,lastName))
				.when().put("users/2")
				.then().extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonPath = jsonPathConverter(response);		
		Assert.assertEquals(jsonPath.getString("firstName"), firstName);
		Assert.assertEquals(jsonPath.getString("lastName"), lastName);
	}


	@Test(priority = 3)
	public void delete() {

		Response response = given()
				.when()
				.delete("users/35")
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(), 404);
	}
}
