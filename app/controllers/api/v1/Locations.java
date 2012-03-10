package controllers.api.v1;

import models.Location;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Locations extends AuthenticatedController {

	public void verify(Long locationId) {
		Location location = Location.findById(locationId);
		
		if (location == null) {
			error(500, "Invalid location id!");
		}
		
		if(LatLngTool.distance(getRequestLatLng(), location.asLatLng(), LengthUnit.METER) < 20) {
			render("OK");
		} else {
			render("ERROR");
		}
	}
}