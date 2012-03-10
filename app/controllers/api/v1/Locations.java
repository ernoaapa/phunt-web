package controllers.api.v1;

import util.DistanceTool;
import models.Location;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Locations extends AuthenticatedController {

	public static void verify(Long locationId) {
		Location location = Location.findById(locationId);
		
		if (location == null) {
			error(500, "Invalid location id!");
		}
		
		if(DistanceTool.isCloseEnoughToFind(getRequestLatLng(), location.asLatLng())) {
			renderJSON("OK");
		} else {
			renderJSON("ERROR");
		}
	}
	
	
	public static void show(Long id) {
		renderJSON(Location.findById(id));
	}
}