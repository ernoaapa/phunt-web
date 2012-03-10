package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import com.javadocmd.simplelatlng.LatLng;
import com.sun.org.apache.bcel.internal.generic.LNEG;

@Entity
public class Location extends Model {

	private Long chainId;

	private Double lat;
	private Double lon;

	private String pictureUrl;

	private Category category;
	
	@OneToMany
	private List<Comment> comments;

	public Long getChainId() {
		return chainId;
	}

	public void setChainId(Long chainId) {
		this.chainId = chainId;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void setLatLng(LatLng latLng) {
		lat = latLng.getLatitude();
		lon = latLng.getLongitude();
	}
	
	public LatLng asLatLng() {
		// TODO: temp fix
		if (lat == null || lon == null) {
			return new LatLng(65.34, 25.23);
		}
		
		return new LatLng(lat, lon);
	}
}