package com.demo.test;

import static org.hamcrest.Matchers.*;
import static com.demo.utils.Formatter.*;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static com.demo.resources.Payload.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class FirstDemo extends BasicSetUp{
	
	@Test
	public static void getUsersByPageNo() {
		int pageno=2;
		Response r= given().queryParam("page",pageno)
				.when().get("users")
				.then().extract().response();
		Assert.assertEquals(r.getStatusCode(),200);
		JsonPath jp = JsonPathResponse(r);

		Assert.assertEquals(jp.getInt("page"),pageno);
		Assert.assertEquals(jp.getInt("per_page"),6);
		Assert.assertEquals(jp.getInt("total"),12);
		Assert.assertEquals(jp.getList("data").size(),6);
		Assert.assertTrue(jp.getString("data[0].email").contains("@reqres.in"));
		Assert.assertEquals(jp.getString("data[1].email"),"lindsay.ferguson@reqres.in");
	}


	@Test
	public void testExample() {

		given().
		get("/users?page=2").
		then().
		statusCode(200).
		body("data[4].first_name",equalTo("George")).
		body("data.first_name",hasItems("George","Rachel"));
	}

    //postMethod
	@Test
	public static void createUser() {

		JSONObject jo = new JSONObject();
		jo.put("name", "Ragavi");
		jo.put("job", "Tester");

		Response r = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(jo.toJSONString())
				      .when().post("/users")
				      .then().extract().response();

		JsonPath jpath = JsonPathResponse(r);

		Assert.assertEquals(r.statusCode(), 201);
		Assert.assertNotNull(jpath.getInt("id"));
		Assert.assertEquals(jpath.getString("name"), "Ragavi");
		Assert.assertEquals(jpath.getString("job"), "Tester");
		Assert.assertNotNull(jpath.getString("createdAt"));
	}

}
