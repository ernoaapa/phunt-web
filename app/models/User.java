package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity(name="phunt_user")
public class User extends Model {

	public String phoneId;
	public String name;

	public User() {		
	}
	
	public User(String uuid, String name) {
		this.phoneId = uuid;
		this.name = name;
	}
	
	public static User findByUuid(String uuid) {
		return User.find("phoneId = ?", uuid).first();
	}

	public static boolean exists(String uuid) {
		return User.findByUuid(uuid) != null;
	}
	
	@Override
	public String toString() {
		return name + " (" + phoneId + ")";
	}
}
