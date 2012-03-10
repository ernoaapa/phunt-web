package services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import play.Logger;
import play.modules.spring.Spring;
import play.mvc.Router;
import play.mvc.Router.Route;
import storage.FileStorage;
import util.L;
import util.M;

import models.Category;
import models.Chain;
import models.Location;

import com.javadocmd.simplelatlng.LatLng;

public class ChainService {

	public List getCategoryHeads(LatLng userLatLng) {
		LocationService locationService = getLocationService();
		
		return L.make(newCategoryContainer(Category.FAST, locationService.getClosestLocationByCategory(userLatLng, Category.FAST)))
				.add(newCategoryContainer(Category.MEDIUM, locationService.getClosestLocationByCategory(userLatLng, Category.MEDIUM)))
				.add(newCategoryContainer(Category.SLOW, locationService.getClosestLocationByCategory(userLatLng, Category.SLOW)))
				.list();
	}

	private HashMap newCategoryContainer(Category category, List<Location> locations) {
		return M.make("categoryName", category)
				.add("chainHeads", locations)
				.map();
	}

	public Location createChain(File image, Category category, LatLng userLatLng) {
		Chain chain = new Chain(category);
		chain.create();
		
		return updateChainHead(chain.id, image, userLatLng);
	}

	public Location updateChainHead(Long chainId, File image, LatLng userLatLng) {
		Chain chain = Chain.findById(chainId);

		Logger.info("Fetching last location for chain#"+chainId);
		Location lastLocation = Location.findLatestByChainId(chainId);
		Logger.info("Fetched latst location: "+lastLocation);
				
		Location newLocation = getLocationService().createLocation(chainId, image, userLatLng, chain.category);
		
		if (lastLocation != null) {
			lastLocation.nextLocationId = newLocation.id;
			lastLocation.save();
		}
		
		chain.save();
		
		return newLocation;	
	}
	
	private LocationService getLocationService() {
		return Spring.getBeanOfType(LocationService.class);
	}

}