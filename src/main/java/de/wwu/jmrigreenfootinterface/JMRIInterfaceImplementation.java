package de.wwu.jmrigreenfootinterface;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.wwu.jmrigreenfootinterface.net.WebSocketClient;

public class JMRIInterfaceImplementation implements JMRIInterface {

	public static String SERVER_HOST;
	public static String SERVER_PORT;
	
	private WebSocketClient client;
	
	public JMRIInterfaceImplementation() {
		loadNetworkConfig();
		
		client = new WebSocketClient(SERVER_HOST, SERVER_PORT);
	}
	
	private void loadNetworkConfig() {
		JSONObject networkConfig = (JSONObject) ConfigIO.getInstance().get("network");
		SERVER_HOST = networkConfig.getString("host");
		SERVER_PORT = networkConfig.getString("port");
		
		System.out.println("Host is " + SERVER_HOST);
		System.out.println("Port is " + SERVER_PORT + "\n");
	}
	
	@Override
	public JSONArray listTypes() {
		try {
			String response = client.doRequest("GET", "type", "");
			if(response != null) {
				return new JSONArray(response);
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONArray getType(String type) {
		try {
			String response = client.doRequest("GET", type, "");
			if(response != null) {
				return new JSONArray(response);
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getItem(String type, String itemName) {
		try {
			String response = client.doRequest("GET", type + "/" + itemName, "");
			if(response != null) {
				return new JSONObject(response);
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getProperty(String type, String itemName, String propertyName) {
		JSONObject item = getItem(type, itemName);
		JSONObject itemData = item.getJSONObject("data");
		return itemData.get(propertyName);
	}

	private boolean setPropertyString(String type, String itemName, String propertyName, String value) {
		try {
			client.doRequest("POST", type + "/" + itemName, "{\"" + propertyName + "\":\"" + String.valueOf(value) + "\"}");
			return getProperty(type, itemName, propertyName).equals(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean setProperty(String type, String itemName, String propertyName, Object value) {
		// if value is of type String, use private helper method to add quotation marks
		if(value instanceof String) {
			return setPropertyString(type, itemName, propertyName, String.valueOf(value));
		}
		
		try {
			client.doRequest("POST", type + "/" + itemName, "{\"" + propertyName + "\":" + String.valueOf(value) + "}");
			return getProperty(type, itemName, propertyName).equals(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
