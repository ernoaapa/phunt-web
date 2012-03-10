package controllers.api.v1;

import play.mvc.Before;
import models.Location;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Locations extends AuthenticatedController {

	@Before(only = { "verify" })
	static void requireLatAndLon() {
		requireLatAndLon();
	}	
	
	public static void verify(Long locationId) {
		Location location = Location.findById(locationId);
		
		if (location == null) {
			error(500, "Invalid location id!");
		}
		
		if(LatLngTool.distance(getRequestLatLng(), location.asLatLng(), LengthUnit.METER) < 20) {
			renderJSON("OK");
		} else {
			renderJSON("ERROR");
		}
	}
	
	
	public static void show(Long id) {
		renderJSON(Location.findById(id));
	}
}