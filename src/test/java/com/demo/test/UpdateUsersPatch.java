package com.demo.test;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UpdateUsersPatch extends BasicSetUp{
	
	@Test(priority = 1)
	public void patchID() {
		
		BasicSetUp.createTest("Update Users Test", "regression");

		JSONObject req=new JSONObject();
		req.put("name","Ragavi");
		req.put("job","EmployeeIn CG");

		System.out.println(req.toJSONString());	
		

		given().
		header("Content_Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		patch("/api/users/2").
		then().
		statusCode(200);
	}

}
