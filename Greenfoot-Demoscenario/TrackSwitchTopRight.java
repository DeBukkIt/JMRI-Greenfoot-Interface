import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine TOP-RIGHT-Switch, d.h. Weiche, die von oben kommende
 * Züge im ungestellten Zustand vertikal leitet, im gestellten Zustand nach rechts.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class TrackSwitchTopRight extends Switch
{    
    /**
     * Erzeugt eine TOP-RIGHT-Switch
     */
    public TrackSwitchTopRight(String name) {
        super(TrackType.SWITCH_TOPRIGHT, "switch_top_right_", name);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case BOTTOM:
                newDirection = this.isSwitched() ? Direction.RIGHT : Direction.BOTTOM;
                break;
            default:
                newDirection = Direction.TOP;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case TOP: return this.isSwitched() ? Direction.LEFT : Direction.BOTTOM;
            case BOTTOM: return Direction.TOP;
            case LEFT: return this.isSwitched() ? Direction.BOTTOM : Direction.RIGHT;
            
            default: return Direction.TOP;
        }
    }
        
}
