package io.rasmi.homelessnyc;

import android.R.color;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
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
		
		faqView.addView(question);
		faqView.addView(answer);

		
		return faqView;
	}
	
	private View webActionView(webAction w) {

		TextView webActionView = new TextView(this);

		webActionView.setText(Html.fromHtml(HTMLify(w.label, w.url)));
		webActionView.setTextSize(16);
		webActionView.setPadding(16, 16, 16, 0);
		
		return webActionView;
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
