package com.demo.localAPI;

import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import static com.demo.utils.Formatter.jsonPathConverter;
import static com.demo.localAPItestdata.Payload.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LocalAPITest extends BaseURL {

	@Test(priority = 0 ,description = "Getting Users")
	public void getUsers() {

		Response response = given().header("Content-Type","Application.json")
				.contentType(ContentType.JSON).accept(ContentType.JSON)
				.when().get("Users")
				.then().extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id[0]"), 1);
		Assert.assertEquals(response.jsonPath().getString("firstName[0]"), "Amritha");
		Assert.assertEquals(response.jsonPath().getString("lastName[0]"), "Roshan");
	}


	@Test(priority = 1,description = "Create Users")
	public void createUser() {


		String firstName ="Puma";
		String lastName= "Ragu";

		Response response = given().header("Content-Type","Application.json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(createUserData(firstName, lastName))
				.when().post("users")
				.then().extract().response();
		JsonPath jsonPath = jsonPathConverter(response);
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertNotNull(response.body());
		Assert.assertEquals(jsonPath.getString("firstName"), firstName);
		Assert.assertEquals(jsonPath.getString("lastName"), lastName);
	}

	@Test(priority = 2,description = "Updating Users")
	public void updateUsersDetails() {

		JSONObject request= new JSONObject();

		String firstName ="Amala";
		String lastName= "Sharma";

		Response response = given().header("Content-Type","Application.json").contentType(ContentType.JSON).accept(ContentType.JSON).
				body(updateUserData(firstName, lastName))
				.when().put("users/2")
				.then().extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonPath = jsonPathConverter(response);		
		Assert.assertEquals(jsonPath.getString("firstName"), firstName);
		Assert.assertEquals(jsonPath.getString("lastName"), lastName);
	}


	@Test(priority = 3,description = "Deleting Users")
	public void deleteUserDetails() {

		Response response = given()
				.when()
				.delete("users/34")
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
