package controllers.api.v1;

import models.Location;
import models.User;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Settings extends AuthenticatedController {

	public static void show() {
		User user = User.findByUuid(getRequestUuid());
		renderJSON(user);
	}
	
	public static void save(String username) {
		User user = User.findByUuid(getRequestUuid());
		user.name = username;
	}	
}