package de.wwu.jmrigreenfootinterface.items;

import de.wwu.jmrigreenfootinterface.JMRI;
import de.wwu.jmrigreenfootinterface.JMRIInterface;

public abstract class JMRIItem {
	
	protected JMRIInterface jmriInterface;
	
	protected String name;

	public JMRIItem(String name) {
		this.name = name;
		
		jmriInterface = JMRI.getInterface();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
