package services;

import controllers.api.v1.AuthenticatedController;
import models.Comment;
import models.User;

public class CommentService {

	public Comment create(String message, Long locationId) {
		Comment comment = new Comment();
		comment.setUser(AuthenticatedController.getUser());
		comment.setMessage(message);
		comment.setLocationId(locationId);
		comment.save();
		return comment;
	}
}