package io.rasmi.homelessnyc;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class facilitiesManager {
	private static final String TAG = "facilitiesManager";
	private static facilitiesManager FacilitiesManager;
	private Context mAppContext;
	private facilityJSONParser JSONParser;
	
	private ArrayList<facility> foodbanks;
	private ArrayList<facility> foodstamps;
	private ArrayList<facility> homebases;
	private ArrayList<facility> libraries;
	private ArrayList<facility> shelters;
	
	private facilitiesManager(Context appContext) {
		mAppContext = appContext;
		JSONParser = new facilityJSONParser(mAppContext);
		try {
			foodbanks = JSONParser.loadFacilities("foodbanks");
			foodstamps = JSONParser.loadFacilities("foodstamps");
			homebases = JSONParser.loadFacilities("homebases");
			libraries = JSONParser.loadFacilities("libraries");
			shelters = JSONParser.loadFacilities("shelters");
		} catch (Exception e) {
			foodbanks = new ArrayList<facility>();
			foodstamps = new ArrayList<facility>();
			homebases = new ArrayList<facility>();
			libraries = new ArrayList<facility>();
			shelters = new ArrayList<facility>();
			Log.e(TAG, "Error loading facilities from JSON file.");
		}
	}
	
	public static facilitiesManager get(Context context) {
		if (FacilitiesManager == null) {
			FacilitiesManager = new facilitiesManager(context.getApplicationContext());
		}
		
		return FacilitiesManager;
	}
	
	public ArrayList<facility> getFacilities(String type) {
		if ("foodbanks".equals(type)) return foodbanks;
		else if ("foodstamps".equals(type)) return foodstamps;
		else if ("homebases".equals(type)) return homebases;
		else if ("libraries".equals(type)) return libraries;
		else if ("shelters".equals(type)) return shelters;
		else return null;
	}
	
	/*
	public facility getFacility(String id) {
		for (facility f : facilities) {
			if (f.id.equals(id))
				return f;
		}
		return null;
	}
	*/
}
