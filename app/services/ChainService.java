package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Category;
import models.Location;

public class ChainService {

	public List findHeads() {
		List categories = new ArrayList();
		categories.add(newCategory("MOTOR", getMockLocations()));
		categories.add(newCategory("BICYCLE", getMockLocations()));
		categories.add(newCategory("FEET", getMockLocations()));
		return categories;
	}

	private HashMap newCategory(String categoryName, List<Location> locations) {
		HashMap category = new HashMap();
		category.put("categoryName", categoryName);
		category.put("chainHeads", getMockLocations());
		return category;
	}
	
	private List<Location> getMockLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(getMockLocation());
		return locations;
	}

	private Location getMockLocation() {
		Location location = new Location();
		location.setRoughDistance("5 km");
		location.setChainId(1L);
		location.setLocationId(1L);
		location.setPictureUrl("http://thekeyresult.com/wp-content/uploads/2011/02/4548.jpg");
		return location;
	}
}