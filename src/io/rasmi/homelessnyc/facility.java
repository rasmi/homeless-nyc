package io.rasmi.homelessnyc;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;


public class facility implements Parcelable {

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


	public facility(String name, String type, String id, String brief_description, String description,
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

	public facility(JSONObject json) throws JSONException {
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
	
	// Implement Parcelable to pass facility objects as Intent extras.
	public facility(Parcel in) {
		String[] data = new String[15];
		in.readStringArray(data);

		this.name = data[0];
		this.type = data[1];
		this.id = data[2];
		this.brief_description = data[3];
		this.description = data[4];
		this.hours = data[5];
		this.eligibility_information = data[6];
		this.address = data[7];
		this.city = data[8];
		this.state = data[9];
		this.zipcode = data[10];
		this.borough = data[11];
		this.additional_address_information = data[12];
		this.latitude = Double.parseDouble(data[13]);
		this.longitude = Double.parseDouble(data[14]);
	}

	@Override
	public int describeContents(){
		return 0;
	}
	
	
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeStringArray(new String[] {
				this.name,
				this.type,
				this.id,
				this.brief_description,
				this.description,
				this.hours,
				this.eligibility_information,
				this.address,
				this.city,
				this.state,
				this.zipcode,
				this.borough,
				this.additional_address_information,
				Double.toString(this.latitude),
				Double.toString(this.longitude)
		});
	}

	public static final Parcelable.Creator<facility> CREATOR = new Parcelable.Creator<facility>() {
		public facility createFromParcel(Parcel in) {
			return new facility(in);
		}
		
		public facility[] newArray(int size) {
			return new facility[size];
		}
	};
	
}

