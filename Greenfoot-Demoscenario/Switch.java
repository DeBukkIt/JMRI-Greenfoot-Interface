import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import de.wwu.jmrigreenfootinterface.*;
import de.wwu.jmrigreenfootinterface.net.*;
import de.wwu.jmrigreenfootinterface.JMRIItems.*;

/**
 * Repräsentiert eine Weiche, d.h. eine besondere Art von Track. Diese Klasse ist abstrakt,
 * um die konkrete Ausprägung der Weiche per Unterklasse zu realisieren, so wie es für alle Tracks der Fall ist.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public abstract class Switch extends Track
{        
    /**
     * Gibt an, ob die Switch gestellt ist. Falls true, leitet sie RailVehicle zur Seite; andernfalls geradeaus.
     */
    private boolean isSwitched; // i.e. switch leads the train to the side (acts like a curve)
    private String imageBaseName = "switch_";
    
    private TurnoutState state;
    private String name;
    
    /**
     * Erzeugt eine Switch vom gegebenen TrackType. Der switched-Zustand wird mit false initialisiert:
     * Standardmäßig soll eine Switch sich darauf bewegende RailVehicle nicht zur Seite leiten.
     */
    public Switch(TrackType trackType, String imageBaseName, String name) {
        super(trackType);
        this.imageBaseName = imageBaseName;
        
        this.state = TurnoutState.CLOSED;
        this.name = name;
    }
    
    /**
     * Setzt den switched-Zustand der Switch, um die Weichenfunktion ein- und auszuschalten.
     * @param switched  true, falls die Switch sich darauf bewegende RailVehicle zur Seite leiten soll; false sonst
     */
    public void setState(TurnoutState state) {
        this.setImage(imageBaseName + (isSwitched() ? "1" : "0") + ".png");
                
        JMRI.getInterface().setProperty("turnout", name, "state", state.getStateCode());
    }
    
    /**
     * Gibt an, ob die Switch gestellt ist.
     * @return true, falls die Switch gestellt ist und sich darauf bewegende RailVehicle zur Seite leitet; false andernfalls
     */
    public boolean isSwitched() {
        // request current state from JMRI
	int stateCode = (int) JMRI.getInterface().getProperty("turnout", name, "state");

	// find corresponding state in TurnoutState enum
	for(TurnoutState someState : TurnoutState.values()) {
		if(someState.getStateCode() == stateCode) {
			this.state = someState;
			break;
		}
	}
        
        return state == TurnoutState.CLOSED ? false : true;
    }
    
    /**
     * Kehrt den switched-Zustand der Switch um.
     */
    public void toggleSwitched() {
        this.setState(this.isSwitched() ? TurnoutState.CLOSED : TurnoutState.THROWN);
    }
        
    /**
     * Bestimmt, auf welchen anderen Track ein RailVehicle in Abhängigkeit von seiner momentanen Direction
     * weitergeleitet wird. Diese Methode wurde aus Track geerbt.
     * @param movingInDirection Die Bewegungsrichtung des fahrenden RailVehicle
     * @return Der Track, auf den das RailVehicle in Abhängigkeit von seiner momentanen Direction
     * weitergeleitet wird.
     */
    public abstract Track determineNextTrack(Direction movingInDirection);
    
    /**
     * Gibt die Direction zurückgibt, die ein RailVehicle auf der Switch haben muss, um in die entgegengesetzte
     * Richtung geleitet zu werden. Diese Methode wurde aus Track geerbt.<br>
     * <b>Achtung:</b> Die Richtung wird nicht einfach zur entgegengesetzten Richtung geändert, sondern so, dass
     * die Switch das darauf befindliche RailVehicle rückwärts leitet.
     * @param movingDirection   Die Bewegungsrichtung des RailVehicle auf dem Track, die umgekehrt werden soll
     * @return Die Direction, die das RailVehicle auf dem Track haben muss, um in die entgegengesetzte Richtung geleitet zu werden.
     */
    public abstract Direction getReversedDirection(Direction movingDirection);
    
}
