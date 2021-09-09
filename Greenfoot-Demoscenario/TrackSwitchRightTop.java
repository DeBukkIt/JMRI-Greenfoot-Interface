import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine RIGHT-TOP-Switch, d.h. Weiche, die von rechts kommende
 * Züge im ungestellten Zustand horizontal leitet, im gestellten Zustand nach oben.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class TrackSwitchRightTop extends Switch
{    
    /**
     * Erzeugt eine RIGHT-TOP-Switch
     */
    public TrackSwitchRightTop(String name) {
        super(TrackType.SWITCH_RIGHTTOP, "switch_right_top_", name);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case LEFT:
                newDirection = this.isSwitched() ? Direction.TOP : Direction.LEFT;
                break;
            default:
                newDirection = Direction.RIGHT;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case LEFT: return this.isSwitched() ? Direction.BOTTOM : Direction.RIGHT;
            case BOTTOM: return this.isSwitched() ? Direction.LEFT : Direction.TOP;
            case RIGHT: return Direction.LEFT;
            
            default: return Direction.LEFT;
        }
    }
        
}
