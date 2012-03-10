package controllers.api.v1;

import javax.inject.Inject;

import models.User;

import org.apache.commons.lang.StringUtils;

import play.mvc.Before;
import play.mvc.Controller;
import services.UserService;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import controllers.api.v1.before.BeforeFilters;

public class AuthenticatedController extends Controller {

	@Inject
	static UserService userService;
	
	@Before
	static void requireUuid() {
		if (StringUtils.isBlank(params.get("uuid"))) {
			error(500, "uuid is required!");
		}
	}

	@Before
	static void createUserIfNecessary() {
		if (!User.exists(params.get("uuid"))) {
			userService.createUser(params.get("uuid"));
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
}