package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import play.modules.spring.Spring;

import models.Category;
import models.Location;

import com.javadocmd.simplelatlng.LatLng;

public class ChainService {

	public List getCategoryHeads(LatLng userLatLng) {
		LocationService locationService = Spring.getBeanOfType(LocationService.class);
		List categoryContainers = new ArrayList();
		categoryContainers.add(newCategoryContainer(Category.MOTOR, locationService.getClosestLocationByCategory(userLatLng, Category.MOTOR)));
		categoryContainers.add(newCategoryContainer(Category.BICYCLE, locationService.getClosestLocationByCategory(userLatLng, Category.BICYCLE)));
		categoryContainers.add(newCategoryContainer(Category.FEET, locationService.getClosestLocationByCategory(userLatLng, Category.FEET)));
		return categoryContainers;
	}

	private HashMap newCategoryContainer(Category category, List<Location> locations) {
		HashMap categoryContainer = new HashMap();
		categoryContainer.put("categoryName", category.toString());
		categoryContainer.put("chainHeads", locations);
		return categoryContainer;
	}

}