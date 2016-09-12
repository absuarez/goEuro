package com.goeurotest.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *  "geo_position":{"latitude":51.50853,"longitude":-0.12574},
 *  
 * @author ana
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GeoPosition {
	
	private Double latitude;
	private Double longitude;
	
	//constructor needed for Jackson
	public GeoPosition() {
	}
	
	//constructor needed for unit testing
	public GeoPosition(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
