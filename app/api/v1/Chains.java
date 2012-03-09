package api.v1;

import java.util.HashMap;
import java.util.Map;

import play.mvc.Controller;

public class Chains extends Controller {

	public static void heads() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("k", "v");
		renderJSON(map);
	}
}
