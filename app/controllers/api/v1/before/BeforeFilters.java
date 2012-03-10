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
	
	public static void requireCommentAndLocationId(Params params) {
		
		if (StringUtils.isBlank(params.get("comment")) || StringUtils.isBlank(params.get("locationId"))) {
			invalidLocationIdOrCommentError();
		}
		
		try {
			Long.parseLong(params.get("locationId"));
		} catch (Exception e) {
			invalidLocationIdOrCommentError();
		}
	}

	private static void invalidLocationIdOrCommentError() {
		throw new Error(500, "comment and locationId are required!");
	}	
}
