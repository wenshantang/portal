package demo;

import java.util.LinkedHashMap;
import java.util.Map;

class DB{
	private static LinkedHashMap<String, MyObject> map = new LinkedHashMap<String, MyObject>();
	static{
		map.put("1", new MyObject("1", "杯子", "12.0"));
		map.put("2", new MyObject("2", "毛巾", "5.0"));
		map.put("3", new MyObject("3", "脸盆", "8.0"));
		map.put("4", new MyObject("4", "肥皂", "6.0" ));
		map.put("5", new MyObject("5", "暖瓶", "18.0"));
	}
	
	public static Map<String, MyObject> getAll(){
		return map;
	}
}