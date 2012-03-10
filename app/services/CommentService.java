package services;

import controllers.api.v1.AuthenticatedController;
import models.Comment;
import models.Location;
import models.User;

public class CommentService {

	public Comment create(String message, Long locationId) {
		
		Comment comment = new Comment();
		comment.user = AuthenticatedController.getUser();
		comment.message = message;
		comment.save();
		
		Location location = Location.findById(locationId);
		location.addComment(comment);
		location.save();
		
		return comment;
	}
}