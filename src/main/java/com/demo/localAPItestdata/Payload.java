package com.demo.localAPItestdata;

import java.util.HashMap;
import java.util.Map;

public class Payload {

	public static Map<String, String> createUserData(String firstName, String lastName) {
		Map<String, String> data = new HashMap<>();
		data.put("firstName", firstName);
		data.put("lastName", lastName);
		return data;
	}

	public static Map<String, String> updateUserData(String firstName, String lastName) {
		Map<String, String> data = new HashMap<>();
		data.put("firstName", firstName);
		data.put("lastName", lastName);
		return data;
	}


}
