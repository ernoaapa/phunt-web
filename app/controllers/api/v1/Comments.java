package controllers.api.v1;

import javax.inject.Inject;

import services.ChainService;
import services.CommentService;
import models.Location;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Comments extends AuthenticatedController {

	@Inject
	private static CommentService commentService;
	
	public static void save() {
		commentService.save(params.get("comment"));		
	}
	
	public static void show() {
				
	}
}