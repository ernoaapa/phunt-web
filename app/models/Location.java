package models;

import com.javadocmd.simplelatlng.LatLng;

import play.db.jpa.Model;

public class Location extends Model {

	private Long locationId;

	private Long chainId;

	private Double lat;
	private Double lon;

	private String roughDistance;

	private String pictureUrl;

	private Category category;

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

	public String getRoughDistance() {
		return roughDistance;
	}

	public void setRoughDistance(String roughDistance) {
		this.roughDistance = roughDistance;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public LatLng asLatLng() {
		return new LatLng(lat, lon);
	}
}