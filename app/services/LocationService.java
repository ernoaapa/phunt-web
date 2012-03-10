package services;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.SimpleLayout;

import play.modules.spring.Spring;
import storage.FileStorage;

import models.Category;
import models.Location;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class LocationService {

	public List getClosestLocationByCategory(final LatLng userLatLng, Category category) {
		List locations = Location.find("category = ?", category).fetch();
		
		Collections.sort(locations, new Comparator<Location>() {
			@Override public int compare(Location loc1, Location loc2) {
				double distanceToLoc1 = LatLngTool.distance(userLatLng, loc1.asLatLng(), LengthUnit.METER);
				double distanceToLoc2 = LatLngTool.distance(userLatLng, loc2.asLatLng(), LengthUnit.METER);
				return (int)(distanceToLoc1 - distanceToLoc2);
			}
		});
		
		return locations;
	}

	public Location createLocation(Long chainId, File image, LatLng userLatLng, Category category) {
		FileStorage fileStorage = Spring.getBeanOfType(FileStorage.class);
		String pictureUrl = fileStorage.save(image);
		
		Location location = new Location();
		location.chainId = chainId;
		location.pictureUrl = pictureUrl;
		location.category = category;
		location.setLatLng(userLatLng);
		location.create();
		
		return location;
	}

}