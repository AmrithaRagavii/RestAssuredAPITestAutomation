package com.demo.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.*;

public class Formatter {
	 public static JsonPath JsonPathResponse(Response r) {
	      return new JsonPath(r.asString());
	    }

}