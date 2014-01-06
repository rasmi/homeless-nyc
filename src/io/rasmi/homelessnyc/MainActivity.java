package io.rasmi.homelessnyc;

import java.util.ArrayList;

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
	private ArrayList<foodBank> foodBanks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		foodButton = (Button)findViewById(R.id.foodbutton);
		foodButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, MapActivity.class);
				startActivity(i);
			}
		});
		
		shelterButton = (Button)findViewById(R.id.shelterbutton);
		shelterButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		resourcesButton = (Button)findViewById(R.id.resourcesbutton);
		resourcesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		foodBanks = facilitiesManager.get(getApplicationContext()).getFoodBanks();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
