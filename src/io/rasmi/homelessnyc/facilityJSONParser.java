package io.rasmi.homelessnyc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class facilityJSONParser {
	private static final String TAG = "facilityJSONParser";
	private Context mContext;
	
	public facilityJSONParser(Context context) { 
		mContext = context;
	}
	
	public ArrayList<facility> loadFacilities() throws IOException, JSONException {
		ArrayList<facility> facilities = new ArrayList<facility>();
		BufferedReader reader = null;
		
		try {
			InputStream in = 
					mContext.getResources().openRawResource(mContext.getResources()
							.getIdentifier("raw/foodbanks","raw", mContext.getPackageName()));
			
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for (int i = 0; i < array.length(); i++) {
				facilities.add(new facility(array.getJSONObject(i)));
			}
			Log.i(TAG, "Loaded " + array.length() + " objects from JSON.");
		} catch(Exception e) {
			Log.e(TAG, "Error loading facilities from JSON. " + e.getLocalizedMessage());
		} finally {
			if (reader != null) 
				reader.close();
		}
		
		return facilities;
	}
}
