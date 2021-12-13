package com.demo.test;
import static com.demo.resources.Payload.createUserData;
import static com.demo.utils.Formatter.jsonPathConverter;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUsers extends BaseURL {
	@Test (priority=1,description ="Create User by using Post Method")
	public static void createUser() {
		String name= "Ragavi";
		String job= "Tester";

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(createUserData(name, job))
				.when().post("/users")
				.then().extract().response();

		JsonPath jpath = jsonPathConverter(response);
		addResponseToReport(response.asPrettyString());

		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertNotNull(jpath.getInt("id"));
		Assert.assertEquals(jpath.getString("name"), name);
		Assert.assertEquals(jpath.getString("job"), job);
		Assert.assertNotNull(jpath.getString("createdAt"));
	}

}
