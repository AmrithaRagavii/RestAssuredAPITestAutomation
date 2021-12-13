package com.demo.test;
import static com.demo.resources.Payload.*;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateUsersPatch extends BaseURL{

	@Test (priority=3,description ="Update User by using Patch Method")
	public void patchID() {
		String name="Amritha";
		String job="Employee in CG";

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(updateUserData(name, job)).
				when().patch("/users/3").
				then().extract().response();

		JsonPath jsonPath = new JsonPath(response.asString());
		addResponseToReport(response.asPrettyString());

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(jsonPath.getString("name"), name);
		Assert.assertEquals(jsonPath.getString("job"),job);
	}
}
