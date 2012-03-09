package controllers.api.v1;

import javax.inject.Inject;

import play.mvc.Before;
import play.mvc.Controller;
import dao.UserDao;

public class AuthenticatedController extends Controller {

	@Inject
	static UserDao userDao;

	@Before
	static void createUserIfNecessary() {
		userDao.findUser();
		System.out.println("checking user!");
	}

}