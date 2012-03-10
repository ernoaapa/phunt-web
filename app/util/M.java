package util;

import java.util.HashMap;
import java.util.Map;

public class M {

	HashMap map;
	
	public M(String key, Object value) {
		map = new HashMap();
		map.put(key, value);
	}
	
	public M add(String key, Object value) {
		map.put(key, value);
		return this;
	}
	
	public HashMap map() {
		return map;
	}
	
	public static M make(String key, Object value) {
		return new M(key, value);
	}
	
}
