package de.wwu.jmrigreenfootinterface.items;

public abstract class JMRITurnout extends JMRIItem {

	public JMRITurnout(String name) {
		super(name);
	}

	public void setState(TurnoutState state) {		
		jmriInterface.setProperty("turnout", name, "state", state.getStateCode());
	}
	
	public TurnoutState getState() {
		// request current state from JMRI
		int resultState = (int) jmriInterface.getProperty("turnout", name, "state");

		// find corresponding state in TurnoutState enum
		for(TurnoutState someState : TurnoutState.values()) {
			if(someState.getStateCode() == resultState) {
				return someState;
			}
		}
		return null;
	}
	
}
