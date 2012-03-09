package controllers.api.v1;

import models.User;

import org.apache.commons.lang.StringUtils;

import play.mvc.Before;
import play.mvc.Controller;

public class AuthenticatedController extends Controller {

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
			// TODO
		}
	}
}