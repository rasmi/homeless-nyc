package io.rasmi.homelessnyc;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class serviceList extends ListActivity {
	private String type;
	private ArrayList<service> services;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		type = getIntent().getStringExtra("type");
		setTitle(getListTitle());
		
		services = servicesManager.get(getApplicationContext()).getServices(type);
		
		serviceAdapter adapter = new serviceAdapter(services);
		setListAdapter(adapter);
		
	}
	
	public void onListItemClick(ListView l, View v, int position, long id){
		service s = ((serviceAdapter) getListAdapter()).getItem(position);
		
		Intent i = new Intent(serviceList.this, serviceInfo.class);
		i.putExtra("service", s);
		i.putExtra("type", type);
		startActivity(i);
	}
	
	private String getListTitle(){
		String title = "";
		if ("resources".equals(type)) title += getResources().getString(R.string.find_resources);
		else if ("community".equals(type)) title += getResources().getString(R.string.help_community);
		return title;
	}
	
	private class serviceAdapter extends ArrayAdapter<service> {
		public serviceAdapter(ArrayList<service> services) {
			super(getApplicationContext(), 0, services);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.list_item_service, null);
			}
			
			service s = getItem(position);
			
			TextView titleText = (TextView) convertView.findViewById(R.id.service_list_item_title);
			titleText.setText(s.name);
			
			TextView descriptionText = (TextView) convertView.findViewById(R.id.service_list_item_description);
			descriptionText.setText(s.brief_description);
			
			return convertView;
		}
	}

}
