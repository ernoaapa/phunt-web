package controllers.api.v1;

import java.io.File;

import javax.inject.Inject;

import models.Category;
import models.Chain;
import services.ChainService;

public class Chains extends AuthenticatedController {

	@Inject
	private static ChainService chainService;

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
}