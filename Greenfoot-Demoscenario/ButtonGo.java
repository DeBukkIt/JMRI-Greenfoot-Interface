import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import de.wwu.jmrigreenfootinterface.*;

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse ButtonGo.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ButtonGo extends Button
{

    private String locomotiveSystemName = "";
    
    public ButtonGo(String locomotiveSystemName) {
        this.locomotiveSystemName = locomotiveSystemName;
    }

    @Override
    public void onClick() {
        JMRI.getInterface().addLocomotive(locomotiveSystemName, locomotiveSystemName); // TODO outsource this line to an extra button
        JMRI.getInterface().setSpeed(locomotiveSystemName, 60);
    }
    
}
