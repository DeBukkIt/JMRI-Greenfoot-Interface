package de.wwu.jmrigreenfootinterface;

import de.wwu.jmrigreenfootinterface.net.JMRIInterface;
import de.wwu.jmrigreenfootinterface.net.JMRIInterfaceImplementation;

public class JMRI {
	
	private JMRI() {}

	private static JMRIInterface jmriInterface;
	
	public static JMRIInterface getInterface() {
		if(jmriInterface == null) {
			jmriInterface = new JMRIInterfaceImplementation();
		}
		return jmriInterface;
	}
	
}
