import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import de.wwu.jmrigreenfootinterface.*;
import de.wwu.jmrigreenfootinterface.net.*;
import de.wwu.jmrigreenfootinterface.items.*;

/**
 * Repräsentiert eine Weiche
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public abstract class Turnout extends Track
{        

    private String imageBaseName = "";
    
    private TurnoutState state = null;
    private String jmriSystemName = "";
    
    public Turnout(String layoutBlockId, String imageBaseName, String jmriSystemName) {
        super(layoutBlockId);
        this.imageBaseName = imageBaseName;
        this.jmriSystemName = jmriSystemName;
        
        this.state = TurnoutState.UNKNOWN;
    }
    
    public Turnout(String imageBaseName, String jmriSystemName) {
        super();
        this.imageBaseName = imageBaseName;
        this.jmriSystemName = jmriSystemName;
        
        this.state = TurnoutState.UNKNOWN;
    }
    
    public void setState(TurnoutState state) {    
        JMRI.getInterface().setProperty("turnout", jmriSystemName, "state", state.getStateCode());
    }
    
    /**
     * Gibt an, ob die Switch gestellt ist.
     * @return true, falls die Switch gestellt ist und sich darauf bewegende RailVehicle zur Seite leitet; false andernfalls
     */
    public TurnoutState getState() {
        // request current state from JMRI
        int stateCode = (int) JMRI.getInterface().getProperty("turnout", jmriSystemName, "state");
        state = TurnoutState.fromCode(stateCode);
        return state;
    }
    
    /**
     * Kehrt den switched-Zustand der Switch um.
     */
    public void toggleSwitched() {
        this.setState(this.isSwitched() ? TurnoutState.CLOSED : TurnoutState.THROWN);
    }
        
    private void updateImage(String imageBaseName, TurnoutState state, boolean isActiveBlock) {
        String stateModifierString = (state == TurnoutState.THROWN ? "thrown" : (state == TurnoutState.CLOSED ? "closed" : "unknown"));
        String activationModifierString = isActiveBlock ? "1" : "0";
        this.setImage(imageBaseName + stateModifierString + "_" + activationModifierString + ".png");
    }    
}
