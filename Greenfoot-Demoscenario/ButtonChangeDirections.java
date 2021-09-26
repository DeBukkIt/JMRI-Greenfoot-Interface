import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import de.wwu.jmrigreenfootinterface.*;
import de.wwu.jmrigreenfootinterface.items.*;

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse ButtonChangeDirections.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ButtonChangeDirections extends Button
{

    private String locomotiveSystemName = "";
    
    public ButtonChangeDirections(String locomotiveSystemName) {
        this.locomotiveSystemName = locomotiveSystemName;
    }
    
    @Override
    public void onClick() {
        doAsync(() -> {
            // remember current speed, then break
            int oldSpeed = JMRI.getInterface().getSpeed(locomotiveSystemName);
            JMRI.getInterface().setSpeed(locomotiveSystemName, 0);
            // wait some seconds for the train to come to a stop
            try { Thread.sleep(5000); } catch(Exception e) { e.printStackTrace(); }
            
            // invert direction
            // TODO add a JMRI method for inverting the direction
            JMRI.getInterface().setMovingDirection(
                locomotiveSystemName,
                JMRI.getInterface().getMovingDirection(locomotiveSystemName) == MovingDirection.FORWARD ?
                    MovingDirection.REVERSE :
                    MovingDirection.FORWARD
               );
               
            // revert old speed
            JMRI.getInterface().setSpeed(locomotiveSystemName, oldSpeed);
        });        
    }
    
}
