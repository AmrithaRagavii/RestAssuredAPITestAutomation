package com.demo.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.*;

public class Formatter {
	public static JsonPath jsonPathConverter(Response r) {
		return new JsonPath(r.asString());
	}
}