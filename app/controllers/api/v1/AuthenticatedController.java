package controllers.api.v1;

import javax.inject.Inject;

import models.User;

import org.apache.commons.lang.StringUtils;

import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http.StatusCode;
import services.UserService;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import controllers.api.v1.before.BeforeFilters;

public class AuthenticatedController extends Controller {

	@Inject
	static UserService userService;
	
	private static User user;
	
	@Before
	static void requireUuid() {
		if (StringUtils.isBlank(params.get("uuid"))) {
			forbidden("uuid is required!");
		}
	}

	@Before
	static void createUserIfNecessary() {
		String uuid = getRequestUuid();
		if (!User.exists(uuid)) {
			user = userService.createUser(uuid);
		} else {
			user = User.findByUuid(uuid);
		}
	}
	
	static String getRequestUuid() {
		return params.get("uuid");
	}
	
	static LatLng getRequestLatLng() {
		Double lat = Double.valueOf(params.get("lat"));
		Double lon = Double.valueOf(params.get("lon"));
		return new LatLng(lat, lon);
	}

	public static User getUser() {
		return user;
	}
}