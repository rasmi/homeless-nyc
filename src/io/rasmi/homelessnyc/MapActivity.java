package io.rasmi.homelessnyc;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity {
	private static final String TAG = "MapActivity";
	private GoogleMap facilityMap;
	private ArrayList<facility> facilities;
	private HashMap<Marker, facility> facilityMarkers = new HashMap<Marker, facility>();
	private String type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		
		type = getIntent().getStringExtra("type");
		facilities = facilitiesManager.get(getApplicationContext()).getFacilities(type);
		
		facilityMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		facilityMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			public void onInfoWindowClick(Marker marker){
				Intent i = new Intent(MapActivity.this, facilityInfo.class);
				i.putExtra("facility", facilityMarkers.get(marker));
				i.putExtra("type", type);
				startActivity(i);
			}
		});
		
		Log.i(TAG, "About to add markers for faciltiies");
		for (facility f : facilities) {
			addMarker(f);
		}
		Log.i(TAG, "Finished adding markers.");
		
		setTitle(getMapTitle());
		centerMap();
	}
	
	private void addMarker(facility f) {
		Marker m = facilityMap.addMarker(new MarkerOptions()
			.position(new LatLng(f.latitude, f.longitude))
			.title(f.name)
			.snippet(f.description));
		
		facilityMarkers.put(m, f);
	}
	
	private void centerMap() {
		facilityMap.setMyLocationEnabled(true);
		LatLng center;
		Location location = facilityMap.getMyLocation();
		
		if (location != null) {
			center = new LatLng(location.getLatitude(), location.getLongitude());
			facilityMap.animateCamera(CameraUpdateFactory.newLatLngZoom(center, 14));
		} else {
			// Use general NYC coordinates if location isn't available.
			center = new LatLng(40.6700, -73.9400);
			facilityMap.animateCamera(CameraUpdateFactory.newLatLngZoom(center, 10));
		}
	}
	
	private String getMapTitle(){
		String title = "Find a ";
		if ("foodbanks".equals(type)) title += getResources().getString(R.string.foodbank);
		else if ("foodstamps".equals(type)) title += getResources().getString(R.string.foodstampcenter);
		else if ("homebases".equals(type)) title += getResources().getString(R.string.homebase_site);
		else if ("libraries".equals(type)) title += getResources().getString(R.string.library);
		
		return title;
	}
	
}
