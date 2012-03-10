package services;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import models.Category;
import models.Location;
import play.modules.spring.Spring;
import play.mvc.Http.Request;
import play.mvc.Router;
import storage.FileStorage;
import util.DistanceTool;
import util.M;

import com.javadocmd.simplelatlng.LatLng;

public class LocationService {

	public List getClosestLocationByCategory(final LatLng userLatLng, Category category) {
		List<Location> locations = Location.find("category = ? AND nextLocationId IS null", category).fetch();
		
		Collections.sort(locations, new Comparator<Location>() {
			@Override public int compare(Location loc1, Location loc2) {
				double distanceToLoc1 = DistanceTool.getDistance(userLatLng, loc1.asLatLng());
				double distanceToLoc2 = DistanceTool.getDistance(userLatLng, loc2.asLatLng());
				return (int)(distanceToLoc1 - distanceToLoc2);
			}
		});
		
		for (Location location : locations) {
			location.updateRequestProperties(userLatLng);
		}
		
		return locations;
	}

	public Location createLocation(Long chainId, File image, LatLng userLatLng, Category category) {
		ImageService imageService = Spring.getBeanOfType(ImageService.class);
		String pictureUrl = imageService.save(image);
		
		Location location = new Location();
		location.chainId = chainId;
		location.pictureUrl = pictureUrl;
		location.category = category;
		location.setLatLng(userLatLng);
		location.create();
		
		return location;
	}

}