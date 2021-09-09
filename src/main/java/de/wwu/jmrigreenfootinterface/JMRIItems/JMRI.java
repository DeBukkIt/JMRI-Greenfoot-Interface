package de.wwu.jmrigreenfootinterface.JMRIItems;

import de.wwu.jmrigreenfootinterface.net.JMRIInterface;
import de.wwu.jmrigreenfootinterface.net.JMRIInterfaceImplementation;

public class JMRI {
	
	// ---- Singleton ----
	
	public static JMRI instance;
	
	private JMRI() {
		
	}
	
	public static JMRI getInstance() {
		if(instance == null) {
			instance = new JMRI();
		}
		return instance;
	}
	
	// ---- ---- ---- ----

	private JMRIInterface jmriInterface;
	
	public JMRIInterface getInterface() {
		if(jmriInterface == null) {
			jmriInterface = new JMRIInterfaceImplementation();
		}
		return jmriInterface;
	}
	
}
