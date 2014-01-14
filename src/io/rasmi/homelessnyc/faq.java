package io.rasmi.homelessnyc;

import org.json.JSONException;
import org.json.JSONObject;

public class faq {
	
	protected String id;
	protected String question;
	protected String url;
	protected String answer;
	protected String answer_html;
	
	public faq(String id, String question, String url, String answer, String answer_html) {
		this.id = id;
		this.question = question;
		this.url = url;
		this.answer = answer;
		this.answer_html = answer_html;
	}
	
	public faq(JSONObject json) throws JSONException {
		this.id = json.getString("id");
		this.question = json.getString("question");
		this.url = json.getString("url");
		this.answer = json.getString("answer");
		this.answer_html = json.getString("answer_html");
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("id", this.id);
		json.put("question", this.question);
		json.put("url", this.url);
		json.put("answer", this.answer);
		json.put("answer_html", this.answer_html);
		
		return json;
	}
}
