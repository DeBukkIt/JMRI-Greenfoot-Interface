package de.wwu.jmrigreenfootinterface;

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
