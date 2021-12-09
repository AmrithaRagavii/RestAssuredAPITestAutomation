package com.demo.test;

import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

public class Delete extends BasicSetUp{
	
	@Test(priority = 2)
	public void deleteID() {
		
		BasicSetUp.createTest("Delete", "regression");
		when().
		delete("/api/users/2").
		then().
		statusCode(204);
		
	}
}

