package controllers.api.v1;

import javax.inject.Inject;

import models.JsonResponse;
import services.ChainService;

public class Chains extends AuthenticatedController {

	@Inject
	private static ChainService chainService;

	public static void heads(String uuid) {
		JsonResponse response = new JsonResponse(chainService.findHeads());
		renderJSON(response);
	}
}