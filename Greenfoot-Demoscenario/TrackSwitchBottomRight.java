import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine BOTTOM-RIGHT-Switch, d.h. Weiche, die von unten kommende
 * Züge im ungestellten Zustand vertikal leitet, im gestellten Zustand nach rechts.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class TrackSwitchBottomRight extends Switch
{    
    /**
     * Erzeugt eine BOTTOM-RIGHT-Switch
     */
    public TrackSwitchBottomRight(String name) {
        super(TrackType.SWITCH_BOTTOMRIGHT, "switch_bottom_right_", name);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case TOP:
                newDirection = this.isSwitched() ? Direction.RIGHT : Direction.TOP;
                break;
            default:
                newDirection = Direction.BOTTOM;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
        
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case TOP: return this.isSwitched() ? Direction.LEFT : Direction.BOTTOM;
            case LEFT: return this.isSwitched() ? Direction.TOP : Direction.RIGHT;
            case BOTTOM: return Direction.TOP;

            default: return Direction.TOP;
        }
    }
            
}
