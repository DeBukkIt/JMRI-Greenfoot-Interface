
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import de.wwu.jmrigreenfootinterface.*;
import de.wwu.jmrigreenfootinterface.items.*;

/**
 * Repräsentiert eine Weiche
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class Turnout extends Track
{
    private String jmriSystemName = "";
    
    public Turnout(TurnoutType turnoutType, String layoutBlockId, String jmriSystemName) {
        super(turnoutType.getImageBaseName(), layoutBlockId);
        this.imageBaseName = imageBaseName;
        this.jmriSystemName = jmriSystemName;
    }
    
    public Turnout(TurnoutType turnoutType, String jmriSystemName) {
        super(turnoutType.getImageBaseName());
        this.imageBaseName = imageBaseName;
        this.jmriSystemName = jmriSystemName;
    }
    
    public void setState(TurnoutState state) {    
        JMRI.getInterface().setProperty("turnout", jmriSystemName, "state", state.getStateCode());
    }
    
    public TurnoutState getState() {
        try {
        // request current state from JMRI
            int stateCode = (int) JMRI.getInterface().getProperty("turnout", jmriSystemName, "state");
            return TurnoutState.fromCode(stateCode);
        } catch(Exception e) {
            System.err.println("Requesting turnout state from JMRI failed: " + e.getMessage() + "\nReturning UNKNOWN state instead.");
            return TurnoutState.UNKNOWN;
        }
    }
    
    public void toggleState() {
        setState(getState() == TurnoutState.CLOSED ? TurnoutState.THROWN : TurnoutState.CLOSED);
    }
        
    public void updateImage() {
        TurnoutState state = getState();
        String stateModifierString = (state == TurnoutState.THROWN ? "thrown" : (state == TurnoutState.CLOSED ? "closed" : "unknown"));
        String activationModifierString = super.isBlockActive() ? "1" : "0";
        this.setImage(this.imageBaseName + stateModifierString + "_" + activationModifierString + ".png");
    }    
}
