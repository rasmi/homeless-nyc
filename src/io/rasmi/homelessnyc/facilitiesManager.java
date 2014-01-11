package io.rasmi.homelessnyc;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class facilitiesManager {
	private static final String TAG = "facilitiesManager";
	private ArrayList<facility> facilities;
	private static facilitiesManager FacilitiesManager;
	private Context mAppContext;
	private facilityJSONParser JSONParser;
	
	private facilitiesManager(Context appContext) {
		mAppContext = appContext;
		JSONParser = new facilityJSONParser(mAppContext);
		try {
			facilities = JSONParser.loadFacilities();
		} catch (Exception e) {
			facilities = new ArrayList<facility>();
			Log.e(TAG, "Error loading facilities from JSON file.");
		}
	}
	
	public static facilitiesManager get(Context context) {
		if (FacilitiesManager == null) {
			FacilitiesManager = new facilitiesManager(context.getApplicationContext());
		}
		
		return FacilitiesManager;
	}
	
	public ArrayList<facility> getFacilities() {
		return facilities;
	}
	
	public facility getFacility(String id) {
		for (facility f : facilities) {
			if (f.id.equals(id))
				return f;
		}
		return null;
	}

}
