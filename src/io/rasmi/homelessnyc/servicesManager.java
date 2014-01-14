package io.rasmi.homelessnyc;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class servicesManager {
	private static final String TAG = "servicesManager";
	private static servicesManager ServicesManager;
	private Context mAppContext;
	private JSONParser parser;
	
	private ArrayList<service> resources;
	private ArrayList<service> community;
	
	private servicesManager(Context appContext) {
		mAppContext = appContext;
		parser = new JSONParser(mAppContext);
		try {
			resources = parser.loadServices("resources");
			community = parser.loadServices("community");
		} catch (Exception e) {
			resources = new ArrayList<service>();
			community = new ArrayList<service>();
			Log.e(TAG, "Error loading services from JSON file.");
		}
	}
	
	public static servicesManager get(Context context) {
		if (ServicesManager == null) {
			ServicesManager = new servicesManager(context.getApplicationContext());
		}
		
		return ServicesManager;
	}
	
	public ArrayList<service> getServices(String type) {
		if ("resources".equals(type)) return resources;
		else if ("community".equals(type)) return community;
		else return null;
	}
	
	/*
	public service getService(String id) {
		for (service s : services) {
			if (s.id.equals(id))
				return s;
		}
		return null;
	}
	*/
}
