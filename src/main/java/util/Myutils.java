package util;

import java.util.HashMap;
import java.util.Map;

public class Myutils {
	public static Map<String, String> getPlaceID() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("광복동", "221112");
		result.put("초량동", "221131");
		result.put("태종대", "221141");
		result.put("전포동", "221152");
		result.put("온천동", "221162");
		result.put("명장동", "221163");
		result.put("대연동", "221172");
		result.put("학장동", "221181");
		result.put("덕천동", "221182");
		result.put("청룡동", "221191");
		result.put("좌동", "221192");
		
		return result;
	}
}
