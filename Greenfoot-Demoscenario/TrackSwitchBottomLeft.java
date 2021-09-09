import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine BOTTOM-LEFT-Switch, d.h. Weiche, die von unten kommende
 * Züge im ungestellten Zustand vertikal leitet, im gestellten Zustand nach links.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class TrackSwitchBottomLeft extends Switch
{    
    /**
     * Erzeugt eine BOTTOM-LEFT-Switch
     */
    public TrackSwitchBottomLeft(String name) {
        super(TrackType.SWITCH_BOTTOMLEFT, "switch_bottom_left_", name);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case TOP:
                newDirection = this.isSwitched() ? Direction.LEFT : Direction.TOP;
                break;
            default:
                newDirection = Direction.BOTTOM;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
        
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case TOP: return this.isSwitched() ? Direction.RIGHT : Direction.BOTTOM;
            case RIGHT: return this.isSwitched() ? Direction.TOP : Direction.LEFT;
            case BOTTOM: return Direction.TOP;

            default: return Direction.TOP;
        }
    }
            
}
