package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Chain extends Model {

	public Category category;

	public Chain(Category category) {
		this.category = category;
	}
	
	public Chain() {
		
	}

}