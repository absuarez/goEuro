package com.goeurotest.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [{"_id":380553,
 * "key":null,
 * "name":"London",
 * "fullName":"London, United Kingdom",
 * "iata_airport_code":null,
 * "type":"location",
 * "country":"United Kingdom",
 * "geo_position":{"latitude":51.50853,"longitude":-0.12574},
 * "locationId":12744,
 * "inEurope":true,
 * "countryId":75,
 * "countryCode":"GB",
 * "coreCountry":true,
 * "distance":null,
 * "names":{"it":"Londra","fi":"Lontoo","es":"Londres","tr":"Londra","pl":"Londyn","pt":"Londres","fr":"Londres","ru":"Лондон","zh":"伦敦","cs":"Londýn","ca":"Londres","nl":"Londen"},
 * "alternativeNames":{}}]
 * 
 * 
 * City model object does not include all the fields returned by the JSON response as they are not needed to be stored on the CSV file
 * @author ana
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class City {
	
	@JsonProperty("_id")
	private Long id;
	private String name;
	private String type;
	
	@JsonProperty("geo_position")
	private GeoPosition geoPosition;
	
	//Constructor needed for Jackson
	public City() {
	}
	
	//Constructor needed for unit testing
	public City(Long id, String name, String type, GeoPosition geoPosition) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.geoPosition = geoPosition;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public GeoPosition getGeoPosition() {
		return geoPosition;
	}
	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}
}
