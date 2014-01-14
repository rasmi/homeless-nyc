package io.rasmi.homelessnyc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class MainActivity extends FragmentActivity {
	
	private Button foodButton;
	private Button shelterButton;
	private Button resourcesButton;
	private Button communityButton;
	private facilitiesManager FacilitiesManager;
	private servicesManager ServicesManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		foodButton = (Button)findViewById(R.id.foodbutton);
		foodButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, MapActivity.class);
				i.putExtra("type", "foodbanks");
				startActivity(i);
			}
		});
		
		shelterButton = (Button)findViewById(R.id.shelterbutton);
		shelterButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, MapActivity.class);
				i.putExtra("type", "shelters");
				startActivity(i);
			}
		});
		
		resourcesButton = (Button)findViewById(R.id.resourcesbutton);
		resourcesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, serviceList.class);
				i.putExtra("type", "resources");
				startActivity(i);
			}
		});
		
		communityButton = (Button)findViewById(R.id.communitybutton);
		communityButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, serviceList.class);
				i.putExtra("type", "community");
				startActivity(i);
			}
		});
		
		FacilitiesManager = facilitiesManager.get(getApplicationContext());
		ServicesManager = servicesManager.get(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
