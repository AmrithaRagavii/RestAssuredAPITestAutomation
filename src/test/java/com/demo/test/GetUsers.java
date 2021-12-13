package com.demo.test;

import static com.demo.utils.Formatter.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetUsers extends BaseURL{
	@Test (priority=0,description ="Get Users List from Page")
	public static void getUsersByPageNo() {
		int pageno=2;

		Response response= given().queryParam("page",pageno)
				.when().get("users")
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(),200);
		JsonPath jp = jsonPathConverter(response);
		addResponseToReport(response.asPrettyString());

		Assert.assertEquals(jp.getInt("page"),pageno);
		Assert.assertEquals(jp.getInt("per_page"),6);
		Assert.assertEquals(jp.getInt("total"),12);
		Assert.assertEquals(jp.getList("data").size(),6);
		Assert.assertTrue(jp.getString("data[0].email").contains("@reqres.in"));
		Assert.assertEquals(jp.getString("data[1].email"),"lindsay.ferguson@reqres.in");
	}
}
