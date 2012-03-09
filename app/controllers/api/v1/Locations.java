package controllers.api.v1;

import javax.inject.Inject;

import services.LocationService;

public class Locations extends AuthenticatedController {

	@Inject
	private static LocationService locationService;

}