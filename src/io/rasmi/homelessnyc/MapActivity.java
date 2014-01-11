package io.rasmi.homelessnyc;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity {
	private static final String TAG = "MapActivity";
	private GoogleMap facilityMap;
	private ArrayList<facility> foodbanks;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		
		facilityMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		foodbanks = facilitiesManager.get(getApplicationContext()).getFacilities();
		
		Log.i(TAG, "About to add markers for foodBanks");
		for (facility f : foodbanks) {
			facilityMap.addMarker(new MarkerOptions()
				.position(new LatLng(f.latitude, f.longitude))
				.title(f.name)
				.snippet(f.description));
		}
		
		Log.i(TAG, "Finished adding markers.");
	}

}
