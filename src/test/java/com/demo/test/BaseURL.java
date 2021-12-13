package com.demo.test;

import static io.restassured.RestAssured.baseURI;
import org.testng.annotations.BeforeTest;

import com.demo.report.ReportManager;

public class BaseURL {

	@BeforeTest
	public void setUp(){
		baseURI="https://reqres.in/api";
	}

	public static void addResponseToReport(String response) {
		ReportManager.logResponse(response); 
	}
}
