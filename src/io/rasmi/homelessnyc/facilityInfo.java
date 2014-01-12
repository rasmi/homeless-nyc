package io.rasmi.homelessnyc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class facilityInfo extends FragmentActivity {
	
	private String type;
	private facility f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facilityview);
		
		Intent i = getIntent();
		type = i.getStringExtra("type");
		f = i.getParcelableExtra("facility");
		
		setTitle(getInfoTitle());
	}

	private String getInfoTitle(){
		String title = "";
		if ("foodbanks".equals(type)) title += getResources().getString(R.string.foodbank);
		else if ("foodstamps".equals(type)) title += getResources().getString(R.string.foodstampcenter);
		else if ("homebases".equals(type)) title += getResources().getString(R.string.homebase_site);
		else if ("libraries".equals(type)) title += getResources().getString(R.string.library);
		
		return title + " Information";
	}
}
