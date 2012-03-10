package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import play.modules.spring.Spring;
import util.L;
import util.M;

import models.Category;
import models.Location;

import com.javadocmd.simplelatlng.LatLng;

public class ChainService {

	public List getCategoryHeads(LatLng userLatLng) {
		LocationService locationService = Spring.getBeanOfType(LocationService.class);
		
		return L.make(newCategoryContainer(Category.MOTOR, locationService.getClosestLocationByCategory(userLatLng, Category.MOTOR)))
				.add(newCategoryContainer(Category.BICYCLE, locationService.getClosestLocationByCategory(userLatLng, Category.BICYCLE)))
				.add(newCategoryContainer(Category.FEET, locationService.getClosestLocationByCategory(userLatLng, Category.FEET)))
				.list();
	}

	private HashMap newCategoryContainer(Category category, List<Location> locations) {
		return M.make("categoryName", category)
				.add("chainHeads", locations)
				.map();
	}

}