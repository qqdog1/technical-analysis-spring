package name.qd.analysis.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
