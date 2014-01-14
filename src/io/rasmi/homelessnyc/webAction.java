package io.rasmi.homelessnyc;

import org.json.JSONException;
import org.json.JSONObject;

public class webAction {
	
	protected String type;
	protected String label;
	protected String url;
	
	public webAction(String type, String label, String url) {
		this.type = type;
		this.label = label;
		this.url = url;
	}
	
	public webAction(JSONObject json) throws JSONException {
		this.type = json.getString("type");
		this.label = json.getString("label");
		this.url = json.getString("url");
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("type", this.type);
		json.put("label", this.label);
		json.put("url", this.url);
		
		return json;
	}
}
