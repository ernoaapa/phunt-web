package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Category;
import models.Location;

public class ChainService {

	public HashMap<Category, List<Location>> findHeads() {

		HashMap<Category, List<Location>> heads = new HashMap<Category, List<Location>>();

		heads.put(Category.MOTOR, getMockLocations());
		heads.put(Category.BICYCLE, getMockLocations());
		heads.put(Category.FEET, getMockLocations());

		return heads;
	}

	private List<Location> getMockLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(getDummyLocation());
		return locations;
	}

	private Location getDummyLocation() {
		Location location = new Location();
		location.setRoughDistance("5 km");
		location.setChainId(1L);
		location.setLocationId(1L);
		location.setPictureUrl("http://thekeyresult.com/wp-content/uploads/2011/02/4548.jpg");
		return location;
	}
}