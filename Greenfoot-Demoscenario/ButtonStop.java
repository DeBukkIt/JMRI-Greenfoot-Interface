import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import de.wwu.jmrigreenfootinterface.*;

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse ButtonStop.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ButtonStop extends Button
{

    private String locomotiveSystemName = "";
    
    public ButtonStop(String locomotiveSystemName) {
        this.locomotiveSystemName = locomotiveSystemName;
    }
    
    @Override
    public void onClick() {
        JMRI.getInterface().doEmergencyStop(locomotiveSystemName);
    }   
    
}