package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Chain extends Model {

	@OneToMany
	public List<Location> locations;

	public void addLocation(Location location) {
		if (locations == null) {
			locations = new ArrayList<Location>();
		}
		
		locations.add(location);
	}

}