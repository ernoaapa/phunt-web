package dao;

import models.User;

public class UserDao {

	public User findUser() {
		System.out.println("finding user!");
		return new User();
	}

}