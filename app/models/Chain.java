package models;

import java.util.List;

import play.db.jpa.Model;

public class Chain extends Model {

	private List<Location> locations;

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

}