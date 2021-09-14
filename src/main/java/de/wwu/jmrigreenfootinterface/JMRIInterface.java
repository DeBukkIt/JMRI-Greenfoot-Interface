package de.wwu.jmrigreenfootinterface;

import org.json.JSONArray;
import org.json.JSONObject;

public interface JMRIInterface {
	
	public JSONArray listTypes();
	
	public JSONArray getType(String type);
	
	public JSONObject getItem(String type, String itemName);
	
	public Object getProperty(String type, String itemName, String propertyName);
	
	public boolean setProperty(String type, String itemName, String propertyName, Object value);

}
