import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine TOP-LEFT-Switch, d.h. Weiche, die von oben kommende
 * Züge im ungestellten Zustand vertikal leitet, im gestellten Zustand nach links.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class TrackSwitchTopLeft extends Switch
{    
    /**
     * Erzeugt eine TOP-LEFT-Switch
     */
    public TrackSwitchTopLeft(String name) {
        super(TrackType.SWITCH_TOPLEFT, "switch_top_left_", name);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case BOTTOM:
                newDirection = this.isSwitched() ? Direction.LEFT : Direction.BOTTOM;
                break;
            default:
                newDirection = Direction.TOP;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case TOP: return Direction.BOTTOM;
            case BOTTOM: return this.isSwitched() ? Direction.RIGHT : Direction.TOP;
            case RIGHT: return this.isSwitched() ? Direction.BOTTOM : Direction.LEFT;
            
            default: return Direction.BOTTOM;
        }
    }
        
}
