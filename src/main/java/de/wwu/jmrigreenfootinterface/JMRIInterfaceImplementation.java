package de.wwu.jmrigreenfootinterface;

import java.io.IOException;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.wwu.jmrigreenfootinterface.items.MovingDirection;
import de.wwu.jmrigreenfootinterface.net.WebSocketClient;
import de.wwu.jmrigreenfootinterface.net.WiThrottleClient;

public class JMRIInterfaceImplementation implements JMRIInterface {

	public static String WEBSERVER_HOST;
	public static String WEBSERVER_PORT;
	
	public static String WITHROTTLESERVER_HOST;
	public static String WITHROTTLESERVER_PORT;
	
	private WebSocketClient webClient;
	private WiThrottleClient throttleClient;
	
	public JMRIInterfaceImplementation() {
		loadNetworkConfig();
		
		webClient = new WebSocketClient(WEBSERVER_HOST, WEBSERVER_PORT);
		throttleClient = new WiThrottleClient(WITHROTTLESERVER_HOST, WITHROTTLESERVER_PORT);
	}
	
	private void loadNetworkConfig() {
		JSONObject networkConfig = (JSONObject) ConfigIO.getInstance().get("network");
		JSONObject webserverConfig = networkConfig.getJSONObject("webserver");
		JSONObject withrottleConfig = networkConfig.getJSONObject("withrottleserver");
		
		WEBSERVER_HOST = webserverConfig.getString("host");
		WEBSERVER_PORT = webserverConfig.getString("port");
		WITHROTTLESERVER_HOST = withrottleConfig.getString("host");
		WITHROTTLESERVER_PORT = withrottleConfig.getString("port");
		
		System.out.println("WebServer config is " + WEBSERVER_HOST + ":" + WEBSERVER_PORT);
		System.out.println("WiThrottle server config is " + WITHROTTLESERVER_HOST + ":" + WITHROTTLESERVER_PORT);
	}
	
	// ============ JMRI json functions section ============
	
	@Override
	public JSONArray listTypes() {
		try {
			String response = webClient.doRequest("GET", "type", "");
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
			String response = webClient.doRequest("GET", type, "");
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
			String response = webClient.doRequest("GET", type + "/" + itemName, "");
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
			webClient.doRequest("POST", type + "/" + itemName, "{\"" + propertyName + "\":\"" + String.valueOf(value) + "\"}");
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
			webClient.doRequest("POST", type + "/" + itemName, "{\"" + propertyName + "\":" + String.valueOf(value) + "}");
			return getProperty(type, itemName, propertyName).equals(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// ============ JMRI WiThrottle functions section ============

	private final String THROTTLE_ID = "T";
	private int lastReceivedSpeed = 0;
	private MovingDirection lastReceivedMovingDirection = MovingDirection.FORWARD;
	
	@Override
	public void addLocomotive(String locomotiveReference, String address) {
		sendCommand("+", locomotiveReference, "<;>", address);
	}

	@Override
	public void removeLocomotive(String locomotiveReference) {
		sendCommand("-", locomotiveReference, "<;>", "r");
	}

	@Override
	public void removeAllLocomotives() {
		removeLocomotive("*");
	}

	@Override
	public void setFunctionKeyPressed(String locomotiveReference, int functionKeyNumber, boolean pressed) {
		sendCommand("A", locomotiveReference, "<;>", "F", pressed ? "1" : "0", String.valueOf(functionKeyNumber));
	}

	@Override
	public void setFunctionKeyLocking(String locomotiveReference, int functionKeyNumber, boolean locking) {
		sendCommand("A", locomotiveReference, "<;>", "m", locking ? "1" : "0", String.valueOf(functionKeyNumber));		
	}

	@Override
	public void setMovingDirection(String locomotiveReference, MovingDirection movingDirection) {
		sendCommand("A", locomotiveReference, "<;>", "R", movingDirection == MovingDirection.FORWARD ? "1" : "0");
	}

	@Override
	public void setSpeed(String locomotiveReference, int speed) {
		sendCommand("A", locomotiveReference, "<;>", "V", String.valueOf(speed));
	}

	@Override
	public MovingDirection getMovingDirection(String locomotiveReference) {
		String[] queryResults = sendCommand("A", locomotiveReference, "<;>", "qR");
		processQueryResults(queryResults, "M" + THROTTLE_ID + "A" + locomotiveReference);
		return lastReceivedMovingDirection;
	}

	@Override
	public int getSpeed(String locomotiveReference) {
		// query current speed of the locomotive
		String[] queryResults = sendCommand("A", locomotiveReference, "<;>", "qV");
		processQueryResults(queryResults, "M" + THROTTLE_ID + "A" + locomotiveReference);
		return lastReceivedSpeed;
	}

	@Override
	public void doEmergencyStop(String locomotiveReference) {
		sendCommand("A", locomotiveReference, "<;>", "X");
	}

	@Override
	public void invertMovingDirection(String locomotiveReference) {
		MovingDirection oldDirection = getMovingDirection(locomotiveReference);
		setMovingDirection(locomotiveReference, oldDirection == MovingDirection.FORWARD ? MovingDirection.REVERSE : MovingDirection.FORWARD);
	}
	
	private String[] sendCommand(String... commandStrings) {
		// build command string
		StringBuilder builder = new StringBuilder();
		for(String s : commandStrings) {
			builder.append(s);
		}
		try {
//			throttleClient.send("M" + THROTTLE_ID + builder.toString());
//			return throttleClient.receive(500);
			
			// \/ BEGIN OF DEBUG \/
			String cmd = "M" + THROTTLE_ID + builder.toString();
			System.out.println("\n>>" + cmd);
			throttleClient.send(cmd);
			String debugStrings[] = throttleClient.receive(500);
			System.out.println("<< " + Arrays.toString(debugStrings) + "\n");
			return debugStrings;
			// /\ END OF DEBUG /\
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void processQueryResults(String[] queryResults, String messagePrefix) {
		// for each part of the (possibly) multi-part result...
		for(String singleResult : queryResults) {
			
			// read and store direction information
			if(singleResult.contains(messagePrefix + "<;>R")) {
				lastReceivedMovingDirection = singleResult.substring(singleResult.indexOf("<;>R") + 4).equalsIgnoreCase("0") ? MovingDirection.REVERSE : MovingDirection.FORWARD;
				
			// read and store speed information
			} else if(singleResult.contains(messagePrefix + "<;>V")) {
				lastReceivedSpeed = Integer.valueOf(singleResult.substring(singleResult.indexOf("<;>V") + 4));
				
			}
			
		}
	}

}
