package controllers.api.v1;

import java.io.File;
import javax.inject.Inject;
import controllers.api.v1.before.BeforeFilters;
import play.mvc.Before;
import play.mvc.Http.StatusCode;
import models.Category;
import models.Chain;
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
		
		Chain chain = chainService.createChain(image, category, getRequestLatLng());
		
		renderJSON(chain);
	}
	
	public static void update(Long chainId, File image) {
		Chain chain = Chain.findById(chainId);
		notFoundIfNull(chain);
		chainService.updateChainHead(chainId, image, getRequestLatLng());
	}
	
}