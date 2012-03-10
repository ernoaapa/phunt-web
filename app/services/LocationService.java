package services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.SimpleLayout;

import util.DistanceTool;

import models.Category;
import models.Location;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class LocationService {

	public List getClosestLocationByCategory(final LatLng userLatLng, Category category) {
		List<Location> locations = Location.find("category = ? AND nextLocationId IS null", category).fetch();
		
		Collections.sort(locations, new Comparator<Location>() {
			@Override public int compare(Location loc1, Location loc2) {
				double distanceToLoc1 = LatLngTool.distance(userLatLng, loc1.asLatLng(), LengthUnit.METER);
				double distanceToLoc2 = LatLngTool.distance(userLatLng, loc2.asLatLng(), LengthUnit.METER);
				return (int)(distanceToLoc1 - distanceToLoc2);
			}
		});
		
		for (Location location : locations) {
			location.roughDistance = DistanceTool.getRoughDistance(userLatLng, location.asLatLng());
		}
		
		
		return locations;
	}

	public Location createLocation(Long chainId, String pictureUrl, LatLng userLatLng, Category category) {
		Location location = new Location();
		location.chainId = chainId;
		location.pictureUrl = pictureUrl;
		location.category = category;
		location.setLatLng(userLatLng);
		location.create();
		
		return location;
	}
}