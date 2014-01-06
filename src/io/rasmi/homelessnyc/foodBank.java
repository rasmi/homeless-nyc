package io.rasmi.homelessnyc;

import org.json.JSONException;
import org.json.JSONObject;

public class foodBank {
	
	protected String name;
	protected String type;
	protected String id;
	protected String brief_description;
	protected String description;
	protected String hours;
	protected String eligibility_information;
	
	protected String address;
	protected String city;
	protected String state;
	protected String zipcode;
	protected String borough;
	protected String additional_address_information;
	protected double latitude;
	protected double longitude;
	
	
	public foodBank(String name, String type, String id, String brief_description, String description,
			String hours, String eligibility_information, String address, String city, String state, String zipcode,
			String borough, String additional_address_information, double latitude, double longitude) {
		
		this.name = name;
		this.type = type;
		this.id = id;
		this.brief_description = brief_description;
		this.description = description;
		this.hours = hours;
		this.eligibility_information = eligibility_information;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.borough = borough;
		this.additional_address_information = additional_address_information;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public foodBank(JSONObject json) throws JSONException {
		this.name = json.getString("facility_name");
		this.type = json.getString("type");
		this.id = json.getString("id");
		this.brief_description = json.getString("brief_description");
		this.description = json.getString("description");
		this.hours = json.getString("displayed_hours");
		this.eligibility_information = json.getString("eligibility_information");
		this.address = json.getString("address");
		this.city = json.getString("city");
		this.state = json.getString("state");
		this.zipcode = json.getString("zipcode");
		this.borough = json.getString("borough");
		this.additional_address_information = json.getString("additional_address_information");
		this.latitude = json.getDouble("latitude");
		this.longitude = json.getDouble("longitude");
	}

}
