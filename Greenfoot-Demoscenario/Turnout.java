import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import de.wwu.jmrigreenfootinterface.*;
import de.wwu.jmrigreenfootinterface.items.*;

/**
 * Repräsentiert eine Weiche
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public abstract class Turnout extends Track
{        
    private TurnoutState state = null;
    private String jmriSystemName = "";
    
    public Turnout(TurnoutType turnoutType, String layoutBlockId, String jmriSystemName) {
        super(turnoutType.getImageBaseName(), layoutBlockId);
        this.imageBaseName = imageBaseName;
        this.jmriSystemName = jmriSystemName;
        
        this.state = TurnoutState.UNKNOWN;
    }
    
    public Turnout(TurnoutType turnoutType, String jmriSystemName) {
        super(turnoutType.getImageBaseName());
        this.imageBaseName = imageBaseName;
        this.jmriSystemName = jmriSystemName;
        
        this.state = TurnoutState.UNKNOWN;
    }
    
    public void setState(TurnoutState state) {    
        JMRI.getInterface().setProperty("turnout", jmriSystemName, "state", state.getStateCode());
    }
    
    public TurnoutState getState() {
        // request current state from JMRI
        int stateCode = (int) JMRI.getInterface().getProperty("turnout", jmriSystemName, "state");
        state = TurnoutState.fromCode(stateCode);
        return state;
    }
    
    public void toggleState() {
        setState(getState() == TurnoutState.CLOSED ? TurnoutState.THROWN : TurnoutState.CLOSED);
    }
        
    public void updateImage() {
        String stateModifierString = (this.state == TurnoutState.THROWN ? "thrown" : (state == TurnoutState.CLOSED ? "closed" : "unknown"));
        String activationModifierString = super.isBlockActive() ? "1" : "0";
        this.setImage(this.imageBaseName + stateModifierString + "_" + activationModifierString + ".png");
    }    
}
