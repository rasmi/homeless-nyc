package io.rasmi.homelessnyc;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class service implements Parcelable {
	private static final String TAG = "service";
	protected String name;
	protected String url;
	protected String id;
	protected String brief_description;
	protected String description;
	protected String description_html;
	protected ArrayList<webAction> webActions = new ArrayList<webAction>();
	protected ArrayList<faq> faqs = new ArrayList<faq>();

	public service(String name, String url, String id, String brief_description, String description,
			String description_html) {

		this.name = name;
		this.url = url;
		this.id = id;
		this.brief_description = brief_description;
		this.description = description;
		this.description_html = description_html;

	}

	public service(JSONObject json) throws JSONException {
		this.name = json.getString("service_name");
		this.url = json.getString("url");
		this.id = json.getString("id");
		this.brief_description = json.getString("brief_description");
		this.description = json.getString("description");
		this.description_html = json.getString("description_html");
		
		JSONArray web_actions = json.getJSONArray("web_actions");
		for (int i = 0; i < web_actions.length(); i++) {
			webActions.add(new webAction(web_actions.getJSONObject(i)));
		}
		
		JSONArray FAQs = json.getJSONArray("faqs");
		for (int i = 0; i < FAQs.length(); i++) {
			faqs.add(new faq(FAQs.getJSONObject(i)));
		}
	}
	
	// Implement Parcelable to pass service objects as Intent extras.
	public service(Parcel in) throws JSONException {
		String[] serviceData = new String[8];
		in.readStringArray(serviceData);

		this.name = serviceData[0];
		this.url = serviceData[1];
		this.id = serviceData[2];
		this.brief_description = serviceData[3];
		this.description = serviceData[4];
		this.description_html = serviceData[5];
		
		
		String webActionJSON = serviceData[6];
		JSONArray web_actions = (JSONArray) new JSONTokener(webActionJSON.toString()).nextValue();
		for (int i = 0; i < web_actions.length(); i++) {
			webActions.add(new webAction(web_actions.getJSONObject(i)));
		}
		
		String faqJSON = serviceData[7];
		JSONArray FAQs = (JSONArray) new JSONTokener(faqJSON.toString()).nextValue();
		for (int i = 0; i < FAQs.length(); i++) {
			faqs.add(new faq(FAQs.getJSONObject(i)));
		}
		
		
	}
	
	private String getJSONString(String type) throws JSONException {
		JSONArray json = new JSONArray();
		
		if ("faqs".equals(type)) {
			for (faq FAQ : faqs) 
				json.put(FAQ.toJSON());
		} else if ("webActions".equals(type)) {
			for (webAction w : webActions) 
				json.put(w.toJSON());
		}

		return json.toString();
	}

	@Override
	public int describeContents(){
		return 0;
	}
	
	
	@Override
	public void writeToParcel(Parcel out, int flags) {
		try {
		out.writeStringArray(new String[] {
				this.name,
				this.url,
				this.id,
				this.brief_description,
				this.description,
				this.description_html,
				getJSONString("webActions"),
				getJSONString("faqs")
		});
		} catch (JSONException e) {
			Log.e(TAG, "Error stringifying JSON to parcel out." + e.getLocalizedMessage());
		}
	}

	public static final Parcelable.Creator<service> CREATOR = new Parcelable.Creator<service>() {
		public service createFromParcel(Parcel in) {
			try {
				return new service(in);
			} catch (JSONException e) {
				Log.e(TAG, "Error creating service parcelable from JSON.");
				return null;
			}
			
		}
		
		public service[] newArray(int size) {
			return new service[size];
		}
	};
	
}

