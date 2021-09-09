package de.wwu.jmrigreenfootinterface.JMRIItems;

import de.wwu.jmrigreenfootinterface.net.JMRIInterface;

public abstract class JMRIItem {
	
	protected JMRIInterface jmriInterface;
	
	protected String name;

	public JMRIItem(String name) {
		this.name = name;
		
		jmriInterface = JMRI.getInstance().getInterface();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
