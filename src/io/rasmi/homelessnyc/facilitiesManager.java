package io.rasmi.homelessnyc;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class facilitiesManager {
	private static final String TAG = "facilitiesManager";
	private ArrayList<foodBank> foodBanks;
	private static facilitiesManager FacilitiesManager;
	private Context mAppContext;
	private facilityJSONParser JSONParser;
	
	private facilitiesManager(Context appContext) {
		mAppContext = appContext;
		JSONParser = new facilityJSONParser(mAppContext);
		try {
			foodBanks = JSONParser.loadFoodBanks();
		} catch (Exception e) {
			foodBanks = new ArrayList<foodBank>();
			Log.e(TAG, "Error loading foodBanks from JSON file.");
		}
	}
	
	public static facilitiesManager get(Context context) {
		if (FacilitiesManager == null) {
			FacilitiesManager = new facilitiesManager(context.getApplicationContext());
		}
		
		return FacilitiesManager;
	}
	
	public ArrayList<foodBank> getFoodBanks() {
		return foodBanks;
	}
	
	public foodBank getFoodBank(String id) {
		for (foodBank fb : foodBanks) {
			if (fb.id.equals(id))
				return fb;
		}
		return null;
	}

}
