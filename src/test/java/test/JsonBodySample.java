package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class JsonBodySample {

	@Test
	public void jsonPayl() {
		Map<String, Object> main = new HashMap<String, Object>();

		Map<String, Object> sub1 = new LinkedHashMap<String, Object>();
		sub1.put("name", "Acme garage");

		Map<String, Object> sub3 = new LinkedHashMap<String, Object>();
		sub3.put("slots", 150);
		sub3.put("Status", 150);

		main.put("custmerInfo", sub1);
		sub1.put("info", sub3);

		List<String> Array = new ArrayList<String>();
		Array.add("77090599269");
		Array.add("12345678910");

		sub1.put("mobile", Array);

		System.out.println(main);

		Map<String, Object> sub2 = new HashMap<String, Object>();
	}
}
