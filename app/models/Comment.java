package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {

	public String message;
	
	@OneToOne
	public User user;

}