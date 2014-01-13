package io.rasmi.homelessnyc;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class facilityInfo extends FragmentActivity {
	
	private String type;
	private facility f;
	private TextView facilityName;
	private TextView facilityDescription;
	private TextView facilityHours;
	private TextView facilityAddress;
	private ImageButton getDirections;
	private String gMapsURL;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facilityview);
		
		
		Intent i = getIntent();
		type = i.getStringExtra("type");
		f = i.getParcelableExtra("facility");
		
		setTitle(getInfoTitle());
		
		final String fullAddress = f.address + ", " + f.city + ", " + f.state + " " + f.zipcode;
		gMapsURL = "http://maps.google.com/maps?daddr=" + fullAddress.replace(" ", "+");
		
		facilityName = (TextView) findViewById(R.id.facilityname);
		facilityName.setText(f.name);
		
		facilityDescription = (TextView) findViewById(R.id.facilitydescription);
		facilityDescription.setText(f.description);
		
		facilityHours = (TextView) findViewById(R.id.facilityhours);
		facilityHours.setText(f.hours.replace("; ", "\n"));
		
		facilityAddress = (TextView) findViewById(R.id.facilityaddress);
		facilityAddress.setText(fullAddress);
		facilityAddress.append("\n" + f.additional_address_information);
		facilityAddress.setPaintFlags(facilityAddress.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		facilityAddress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getDirections();
			}
		});
		
		getDirections = (ImageButton) findViewById(R.id.getDirections);
		getDirections.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getDirections();
			}
		});
	}
	
	private void getDirections() {
		Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(gMapsURL));
		startActivity(i);
	}

	private String getInfoTitle(){
		String title = "";
		if ("foodbanks".equals(type)) title += getResources().getString(R.string.foodbank);
		else if ("foodstamps".equals(type)) title += getResources().getString(R.string.foodstampcenter);
		else if ("homebases".equals(type)) title += getResources().getString(R.string.homebase_site);
		else if ("libraries".equals(type)) title += getResources().getString(R.string.library);
		else if ("shelters".equals(type)) title += f.type;
		
		return title + " Information";
	}
}
