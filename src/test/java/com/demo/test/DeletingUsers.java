package com.demo.test;

import static io.restassured.RestAssured.when;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DeletingUsers extends BaseURL{

	@Test (priority=4,description ="Delete User by using Delete Method")
	public void deleteID() {


		Response response=when().
				delete("/api/users/2").
				then().extract().response();
		Assert.assertEquals(response.statusCode(), 204);
		addResponseToReport(response.asPrettyString());

	}
}

