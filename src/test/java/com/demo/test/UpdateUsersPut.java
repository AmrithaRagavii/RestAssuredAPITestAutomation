package com.demo.test;
import static com.demo.resources.Payload.*;
import static io.restassured.RestAssured.*;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class UpdateUsersPut extends BaseURL {

	@Test (priority=2,description ="Update User by using Put Method")
	public static void updateForPut() {
		String name="Amritha";
		String job="Baker";

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(updateUserData(name, job)).
				when().put("/users/2").
				then().extract().response();

		JsonPath jsonPath = new JsonPath(response.asString());
		addResponseToReport(response.asPrettyString());

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(jsonPath.getString("name"), name);
		Assert.assertEquals(jsonPath.getString("job"),job);
	}
}
