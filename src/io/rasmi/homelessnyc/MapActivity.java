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
	private GoogleMap foodMap;
	private ArrayList<foodBank> foodBanks;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		
		foodMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		foodBanks = facilitiesManager.get(getApplicationContext()).getFoodBanks();
		Log.i(TAG, "About to add markers for foodBanks");
		for (foodBank fb : foodBanks) {
			foodMap.addMarker(new MarkerOptions()
				.position(new LatLng(fb.latitude, fb.longitude))
				.title(fb.name)
				.snippet(fb.description));
		}
		Log.i(TAG, "Finished adding markers.");
		
	}

}
