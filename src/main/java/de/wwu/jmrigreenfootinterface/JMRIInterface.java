package de.wwu.jmrigreenfootinterface;

import org.json.JSONArray;
import org.json.JSONObject;

import de.wwu.jmrigreenfootinterface.items.MovingDirection;

public interface JMRIInterface {
	
	// JMRI json functions
	
	public JSONArray listTypes();
	
	public JSONArray getType(String type);
	
	public JSONObject getItem(String type, String itemName);
	
	public Object getProperty(String type, String itemName, String propertyName);
	
	public boolean setProperty(String type, String itemName, String propertyName, Object value);

	// JMRI WiThrottle functions
	
	public void addLocomotive(String locomotiveReference, String address);
	
	public void removeLocomotive(String locomotiveReference);
	
	public void removeAllLocomotives();
	
	public void setFunctionKeyPressed(String locomotiveReference, int functionKeyNumber, boolean pressed);
	
	public void setFunctionKeyLocking(String locomotiveReference, int functionKeyNumber, boolean locking);
	
	public void setMovingDirection(String locomotiveReference, MovingDirection movingDirection);
	
	public void setSpeed(String locomotiveReference, int speed);
	
	public MovingDirection getMovingDirection(String locomotiveReference);
	
	public int getSpeed(String locomotiveReference);
	
	public void doEmergencyStop(String locomotiveReference);
	
}
