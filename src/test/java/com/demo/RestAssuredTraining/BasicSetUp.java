package com.demo.RestAssuredTraining;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeTest;

public class BasicSetUp {
	@BeforeTest
	public void setUp(){
		baseURI="https://reqres.in/api";
	}

}
