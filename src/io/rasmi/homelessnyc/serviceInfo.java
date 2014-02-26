package io.rasmi.homelessnyc;

import android.R.color;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class serviceInfo extends FragmentActivity {
	
	private String type;
	private service s;
	private LinearLayout serviceViewLayout;
	private TextView serviceName;
	private TextView serviceDescription;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serviceview);
		
		serviceViewLayout = (LinearLayout) findViewById(R.id.serviceviewlayout);
		
		Intent i = getIntent();
		type = i.getStringExtra("type");
		s = i.getParcelableExtra("service");
		
		setTitle(getInfoTitle());
		
		
		serviceName = (TextView) findViewById(R.id.servicename);
		serviceName.setText(s.name);
		
		serviceDescription = (TextView) findViewById(R.id.servicedescription);
		serviceDescription.setText(Html.fromHtml(s.description_html));

		addWebActions();
		addFAQs();
		addMaps();

	}
	
	private void addFAQs() {
		if (s.faqs.isEmpty()) {
			findViewById(R.id.faqseparator).setVisibility(View.INVISIBLE);
		} else {
			for (faq f : s.faqs) 
				serviceViewLayout.addView(faqView(f));
		}
	}
	
	private void addWebActions() {
		if (s.webActions.isEmpty()) {
			findViewById(R.id.webactionseparator).setVisibility(View.INVISIBLE);
		} else {
			int index = serviceViewLayout.indexOfChild((TextView) findViewById(R.id.webactionseparator));
			for (webAction w: s.webActions)
				serviceViewLayout.addView(webActionView(w), ++index);
		}
	}
	
	private void addMaps() {
		if (s.name.contains("Food Stamp"))
			addMap("foodstamps");
		else if (s.name.contains("Library"))
			addMap("libraries");
		else if (s.name.contains("HomeBase"))
			addMap("homebases");
		else if (s.name.contains("Intake"))
			addMap("shelters");
	}
	
	private void addMap(String type) {
		int index = serviceViewLayout.indexOfChild((TextView) findViewById(R.id.servicedescription));
		serviceViewLayout.addView(mapView(type), ++index);
	}
	
	private View faqView(faq f) {
		LinearLayout faqView = new LinearLayout(this);
		faqView.setBackgroundColor(color.white);
		faqView.setOrientation(LinearLayout.VERTICAL);
		faqView.setPadding(16, 16, 16, 16);
		
		TextView question = new TextView(this);
		question.setText(f.question);
		question.setTextSize(16);
		question.setPadding(0, 0, 0, 16);
		question.setTypeface(null, Typeface.BOLD);
		TextView answer = new TextView(this);
		answer.setText(Html.fromHtml(f.answer_html));
		answer.setMovementMethod(LinkMovementMethod.getInstance());
		
		faqView.addView(question);
		faqView.addView(answer);

		
		return faqView;
	}
	
	private View webActionView(webAction w) {

		TextView webActionView = new TextView(this);

		if ("".equals(w.url))
			webActionView.setText(w.label);
		else
			webActionView.setText(Html.fromHtml(HTMLify(w.label, w.url)));
		
		webActionView.setTextSize(16);
		webActionView.setPadding(16, 16, 16, 0);
		webActionView.setMovementMethod(LinkMovementMethod.getInstance());
		
		return webActionView;
	}
	
	private View mapView(String type) {
		
		final String mapType = type;
		
		ImageButton mapButton = new ImageButton(this);
		mapButton.setImageResource(R.drawable.ic_dialog_map);
		mapButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(serviceInfo.this, MapActivity.class);
				i.putExtra("type", mapType);
				startActivity(i);
			}
		});
		
		mapButton.setBackgroundColor(Color.parseColor("#33b5e5"));
		
		return mapButton;
	}
	
	private String HTMLify(String text, String url) {
		return "&#8226;  <a href=\"" + url + "\">" + text + "</a>";
	}

	private String getInfoTitle(){
		String title = "";
		if ("resources".equals(type)) title += getResources().getString(R.string.resource);
		else if ("community".equals(type)) title += getResources().getString(R.string.opportunity);
		
		return title + " Information";
	}
}
