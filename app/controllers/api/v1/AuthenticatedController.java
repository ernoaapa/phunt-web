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
	static void requireLatAndLon() {
		if (StringUtils.isBlank(params.get("lat")) || StringUtils.isBlank(params.get("lon"))) {
			error(500, "lat and lon are required!");
		}
	}

	@Before
	static void createUserIfNecessary() {
		if (!User.exists(params.get("uuid"))) {
			userService.createUser(params.get("uuid"));
		}
	}
	
	static LatLng getRequestLatLng() {
		Double lat = Double.valueOf(params.get("lat"));
		Double lon = Double.valueOf(params.get("lon"));
		return new LatLng(lat, lon);
	}
	
	static Double getDistance(LatLng point1, LatLng point2) {
		return LatLngTool.distance(point1, point2, LengthUnit.METER);
	}
	
	static String getRoughDistance(LatLng point1, LatLng point2) {
		double meters = getDistance(point1, point2);
		
		if (meters > 100000f) {
			return "100+ km";
		}
		
		if (meters > 80000f) {
			return "80-100 km";
		}
		
		if (meters > 60000f) {
			return "60-80 km";
		}
		
		if (meters > 40000) {
			return "40-60 km";
		}
		
		if (meters > 20000) {
			return "20-40 km";
		}
		
		if (meters > 10000) {
			return "10-20 km";
		}
		
		if (meters > 8000) {
			return "8-10 km";
		}
		
		if (meters > 6000) {
			return "6-8 km";
		}
		
		if (meters > 4000) {
			return "4-6 km";
		}
		
		if (meters > 2000) {
			return "2-4 km";
		}
		
		if (meters > 1000) {
			return "1-2 km";
		}
		
		if (meters > 500) {
			return "500-1000 m";
		}
		
		if (meters > 100) {
			return "100-500 m";
		}
		
		return "very close";
	}
	
}