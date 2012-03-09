package utils;

public class StringUtils {

	public static boolean isBlank(String s) {
		if (s == null || "".equals(s)) {
			return true;
		}
		return false;
	}

}
