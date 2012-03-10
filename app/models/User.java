package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity(name="phunt_user")
public class User extends Model {

	private String phoneId;
	private String name;

	public User() {
		
	}
	
	public User(String uuid, String name) {
		this.phoneId = uuid;
		this.name = name;
	}
	
	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String uuid) {
		this.phoneId = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static User findByUuid(String uuid) {
		return User.find("uuid = ?", uuid).first();
	}

	public static boolean exists(String uuid) {
		if (uuid != null) {
			return false;
		}	
		
		return User.findByUuid(uuid) != null;		
	}
}
