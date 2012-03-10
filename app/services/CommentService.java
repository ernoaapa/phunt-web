package services;

import controllers.api.v1.AuthenticatedController;
import models.Comment;
import models.User;

public class CommentService {

	public Comment createComment(String uuid) {
		Comment comment = new Comment();
		comment.setUser(AuthenticatedController.getUser());
		comment.save();
		return comment;
	}
}