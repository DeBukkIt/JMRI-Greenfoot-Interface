package de.wwu.jmrigreenfootinterface.JMRIItems;

public enum TurnoutState {

	THROWN(4), CLOSED(2);
	
	private int stateCode;
	
	private TurnoutState(int stateCode) {
		this.stateCode = stateCode;
	}
	
	public int getStateCode() {
		return stateCode;
	}
	
}
