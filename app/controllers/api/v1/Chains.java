package controllers.api.v1;

import java.io.File;
import javax.inject.Inject;
import controllers.api.v1.before.BeforeFilters;
import play.Logger;
import play.mvc.Before;
import play.mvc.Http.StatusCode;
import models.Category;
import models.Chain;
import models.Location;
import services.ChainService;
import services.LocationService;

public class Chains extends AuthenticatedController {

	@Inject
	private static ChainService chainService;


	@Before(only = { "heads", "create", "update" })
	static void requireLatAndLon() {
		BeforeFilters.requireLatAndLon(params);
	}

	public static void heads() {
		renderJSON(chainService.getCategoryHeads(getRequestLatLng()));
	}
	
	public static void create(File image, Category category) {
		if (image == null) {	
			error(StatusCode.BAD_REQUEST, "Chain start image is required!");
		}
		
		Location chainHead = chainService.createChain(image, category, getRequestLatLng(), AuthenticatedController.getUser().phoneId);
		
		renderJSON(chainHead.getResourceUrl());
	}
	
	public static void update(Long chainId, File image) {
		Logger.info("Updating head of Chain#"+chainId+": Image: "+image.getName()+" Space:"+image.getTotalSpace());
		
		Chain chain = Chain.findById(chainId);
		notFoundIfNull(chain);
		chainService.updateChainHead(chainId, image, getRequestLatLng(), AuthenticatedController.getUser().phoneId);
		Location location = Location.findLatestByChainId(chainId);
		
		Logger.info("Replying with resource url: " + location.getResourceUrl());
		renderJSON(location.getResourceUrl());
	}
	
}