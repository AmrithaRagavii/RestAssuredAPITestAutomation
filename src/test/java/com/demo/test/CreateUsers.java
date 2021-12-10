package com.demo.test;

import static com.demo.utils.Formatter.jsonPathConverter;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUsers {
	@Test
	public static void createUser() {

		JSONObject jo = new JSONObject();
		jo.put("name", "Ragavi");
		jo.put("job", "Tester");

		Response r = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(jo.toJSONString())
				.when().post("/users")
				.then().extract().response();

		JsonPath jpath = jsonPathConverter(r);

		Assert.assertEquals(r.statusCode(), 201);
		Assert.assertNotNull(jpath.getInt("id"));
		Assert.assertEquals(jpath.getString("name"), "Ragavi");
		Assert.assertEquals(jpath.getString("job"), "Tester");
		Assert.assertNotNull(jpath.getString("createdAt"));
	}

}
