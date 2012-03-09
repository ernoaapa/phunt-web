package controllers.api.v1;

import models.User;
import play.mvc.Before;
import play.mvc.Controller;

public class AuthenticatedController extends Controller {

	@Before
	static void createUserIfNecessary() {
		if (userExists()) {

		}

		User user = User.findById(uuid);
	}

	private static boolean userExists() {
		String uuid = params.get("uuid");
		if (uuid != null) {

		}
		return false;
	}
}