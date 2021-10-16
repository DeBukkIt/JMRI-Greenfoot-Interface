import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import de.wwu.jmrigreenfootinterface.*;

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse ButtonHorn.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ButtonHorn extends Button
{

    private String locomotiveSystemName = "";
    
    public ButtonHorn(String locomotiveSystemName) {
        this.locomotiveSystemName = locomotiveSystemName;
    }

    @Override
    public void onClick() {
        JMRI.getInterface().setFunctionKeyPressed(locomotiveSystemName, 8, true);
    }
    
}
