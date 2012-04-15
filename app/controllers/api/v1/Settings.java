package controllers.api.v1;

import org.springframework.jdbc.core.JdbcTemplate;

import play.db.DB;
import models.Location;
import models.User;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Settings extends AuthenticatedController {

	public static void show() {
		renderJSON(AuthenticatedController.getUser());
	}
	
	public static void save(String username) {
		User user = AuthenticatedController.getUser();
		user.name = username;
		user.save();
	}	
}