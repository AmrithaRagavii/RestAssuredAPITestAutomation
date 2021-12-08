package com.demo.resources;

import java.util.HashMap;
import java.util.Map;

public class Payload {
	
	public static Map<String, String> createUserData(String name, String job) {
        Map<String, String> m = new HashMap<>();
        m.put("name", name);
        m.put("job", job);
        return m;
    }

    public static Map<String, String> updateUserData(String name, String job, String role) {
        Map<String, String> m = new HashMap<>();
        m.put("name", name);
        m.put("job", job);
        m.put("role", role);
        return m;
    }

}
