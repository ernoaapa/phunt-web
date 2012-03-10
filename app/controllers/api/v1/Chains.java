package controllers.api.v1;

import java.io.File;

import javax.inject.Inject;

import controllers.api.v1.before.BeforeFilters;

import play.mvc.Before;

import models.Category;
import models.Chain;
import models.Location;
import services.ChainService;
import services.LocationService;

public class Chains extends AuthenticatedController {

	@Inject
	private static ChainService chainService;

	@Inject
	private static LocationService locationService;
	

	@Before(only = { "heads", "create", "update" })
	static void requireLatAndLon() {
		BeforeFilters.requireLatAndLon(params);
	}

	public static void heads() {
		renderJSON(chainService.getCategoryHeads(getRequestLatLng()));
	}
	
	public static void create(File image, Category category) {
		if (image == null) {
			error(500, "Chain start image is required!");
		}
		
		Chain chain = chainService.createChain(image, category, getRequestLatLng());
		
		renderJSON(chain);
	}
	
	public static void update(Long chainId, File image) {
		Chain chain = Chain.findById(chainId);
		if (chain == null) {
			error(500, "Invalid chain id");
		}
		
		Location location = locationService.createLocation(chain.id, image, getRequestLatLng(), chain.category);
		
		chainService.updateChainHead(chainId, location);
	}
	
	public static void updateChainHead(Long chainId, Double lat, Double lon) {
		
	}
}