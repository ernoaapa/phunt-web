package controllers.api.v1;

import java.io.File;

import javax.inject.Inject;

import models.Category;
import models.Chain;
import models.Location;

import com.javadocmd.simplelatlng.LatLng;

import services.ChainService;
import storage.FileStorage;

public class Chains extends AuthenticatedController {

	@Inject
	private static ChainService chainService;

	@Inject
	private static FileStorage fileStorage;
	
	public static void heads() {
		renderJSON(chainService.getCategoryHeads(getRequestLatLng()));
	}
	
	public static void create(File image, Category category) {
		if (image == null) {
			error(500, "Chain start image is required!");
		}
		
		String pictureUrl = fileStorage.save(image);
		
		Chain chain = new Chain();
		
		Location location = new Location();
		location.setLatLng(getRequestLatLng());
		location.setPictureUrl(pictureUrl);
		location.setCategory(category);
		location.create();
		
		chain.addLocation(location);
		chain.create();
		
		location.setChainId(chain.getId());
		location.save();
		
		renderJSON(chain);
	}
}