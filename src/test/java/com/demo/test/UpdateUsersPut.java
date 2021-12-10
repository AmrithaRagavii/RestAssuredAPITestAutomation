package com.demo.test;

import static io.restassured.RestAssured.*;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class UpdateUsersPut extends BasicSetUp {

	@Test(priority = 0)
	public static void updateForPut() {

		JSONObject putRequest = new JSONObject();

		putRequest.put("name", "Amritha");
		putRequest.put("job", "Baker");

		Response rep = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(putRequest.toJSONString()).
				when().put("/users/2").
				then().extract().response();

		JsonPath jsonPath = new JsonPath(rep.asString());

		Assert.assertEquals(rep.statusCode(), 200);
		Assert.assertEquals(jsonPath.getString("name"), "Amritha");
		Assert.assertEquals(jsonPath.getString("job"), "Baker");
		Assert.assertNotNull(jsonPath.getString("updatedAt"));

	}
}
