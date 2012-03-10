package services;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Category;
import models.Chain;
import models.Location;
import play.Logger;
import play.modules.spring.Spring;
import util.DistanceTool;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.javadocmd.simplelatlng.LatLng;

public class LocationService {

	public List getClosestLocationByCategory(final LatLng userLatLng, Category category) {
		
		List<Location> locations = Location.find("select distinct l from Chain c JOIN c.locations l where c.category = :category AND nextLocationId IS null")
			.bind("category", category).fetch();
		
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
		
		Logger.info("Created "+location);
		
		return location;
	}

}