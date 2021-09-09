package de.wwu.jmrigreenfootinterface;

import de.wwu.jmrigreenfootinterface.net.JMRIInterfaceImplementation;

public class StartJMRIGreenfootInterfaceTest {
	
	public static void main(String[] args) {
		
		// Interface test
		
		JMRIInterfaceImplementation impl = new JMRIInterfaceImplementation();
		System.out.println(impl.listTypes().toString());
		System.out.println(impl.getType("turnout"));
		System.out.println(impl.getItem("turnout", "ITabc123"));
		System.out.println(impl.getProperty("turnout", "ITabc123", "feedbackModes"));
		System.out.println(impl.setProperty("turnout", "ITabc123", "inverted", false));
		System.out.println(impl.setProperty("turnout", "ITabc123", "comment", "Another comment"));
		
		
		// Turnout state change test
		
		GreenfootTestTurnout to = new GreenfootTestTurnout("ITabc123");
		System.out.println(to.getState());
		
		to.setState(TurnoutState.CLOSED);
		System.out.println(to.getState());
		
		try { Thread.sleep(2000); } catch (InterruptedException e) {}
		
		to.setState(TurnoutState.THROWN);
		System.out.println(to.getState());
		
	}

}
