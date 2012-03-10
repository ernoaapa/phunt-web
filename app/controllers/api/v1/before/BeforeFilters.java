package controllers.api.v1.before;

import org.apache.commons.lang.StringUtils;

import play.mvc.Before;
import play.mvc.Controller;

public class BeforeFilters extends Controller {

	public static void requireLatAndLon() {
		if (StringUtils.isBlank(params.get("lat")) || StringUtils.isBlank(params.get("lon"))) {
			error(500, "lat and lon are required!");
		}
	}	
	
}
