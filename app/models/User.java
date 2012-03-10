package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	private String uuid;
	private String name;

	public User() {
		
	}
	
	public User(String uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
