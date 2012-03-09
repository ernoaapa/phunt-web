package controllers.api.v1;

import java.util.HashMap;
import java.util.Map;

public class Chains extends AuthenticatedController {

	public static void heads(String uuid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("k", "v");
		renderJSON(map);
	}
}