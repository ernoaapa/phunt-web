package services;

import controllers.api.v1.AuthenticatedController;
import models.Comment;
import models.User;

public class CommentService {

	public Comment save(String message) {
		Comment comment = new Comment();
		comment.setUser(AuthenticatedController.getUser());
		comment.setMessage(message);
		comment.save();
		return comment;
	}
}