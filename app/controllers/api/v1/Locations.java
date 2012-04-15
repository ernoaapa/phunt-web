package controllers.api.v1;


import models.Location;
import play.mvc.Before;
import util.DistanceTool;
import controllers.api.v1.before.BeforeFilters;

public class Locations extends AuthenticatedController {

	@Before(only = { "verify" })
	static void requireLatAndLon() {
		BeforeFilters.requireLatAndLon(params);
	}	
	
	public static void verify(Long locationId) {
		if (locationId == null) {
			error(500, "Location id required!");
			
		}
				
		Location location = Location.findById(locationId);
		
		if (location == null) {
			error(500, "Invalid location id!");
		}
		
		if(DistanceTool.isCloseEnoughToFind(getRequestLatLng(), location.asLatLng())) {
			renderText("Close enough");
		} else {
			error("Too far");
		}
	}
	
	
	public static void show(Long id) {
		renderJSON(Location.findById(id));
	}
}