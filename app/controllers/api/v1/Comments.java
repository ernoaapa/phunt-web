package controllers.api.v1;

import javax.inject.Inject;

import play.mvc.Before;
import play.mvc.results.Error;

import services.ChainService;
import services.CommentService;
import models.Location;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import controllers.api.v1.before.BeforeFilters;

public class Comments extends AuthenticatedController {

	@Inject
	private static CommentService commentService;
	
	@Before(only = { "create" })
	static void requireCommentAndLocationId() {
		BeforeFilters.requireCommentAndLocationId(params);
	}	
	
	public static void create() {
		commentService.create(params.get("comment"), getLocationId());
	}

	private static Long getLocationId() {
		return Long.parseLong(params.get("locationId"));
	}
}