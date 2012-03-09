package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	private String uuid;
	private String name;

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
		JPAQuery query = User.find("uuid = ?", uuid);
		return query.first();
	}

	public static boolean exists(String uuid) {
		if (uuid != null) {
			if (User.findByUuid(uuid) != null) {
				return true;
			}
		}
		return false;
	}
}
