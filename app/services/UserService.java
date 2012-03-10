package services;

import models.User;

public class UserService {

	public User createUser(String uuid) {
		User user = new User(uuid, "Unknown");
		user.save();
		return user;
	}
}