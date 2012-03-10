package controllers.api.v1.before;

import org.apache.commons.lang.StringUtils;

import play.classloading.enhancers.ControllersEnhancer.ControllerSupport;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope.Params;
import play.mvc.results.Error;

public class BeforeFilters {

	public static void requireLatAndLon(Params params) {
		if (StringUtils.isBlank(params.get("lat")) || StringUtils.isBlank(params.get("lon"))) {
			throw new Error(500, "lat and lon are required!");
		}
	}	
}
