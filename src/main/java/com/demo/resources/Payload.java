package com.demo.resources;

import java.util.HashMap;
import java.util.Map;

public class Payload {

	public static Map<String, String> createUserData(String name, String job) {
		Map<String, String> data = new HashMap<>();
		data.put("name", name);
		data.put("job", job);
		return data;
	}

	public static Map<String, String> updateUserData(String name, String job) {
		Map<String, String> data = new HashMap<>();
		data.put("name", name);
		data.put("job", job);
		return data;
	}

}
