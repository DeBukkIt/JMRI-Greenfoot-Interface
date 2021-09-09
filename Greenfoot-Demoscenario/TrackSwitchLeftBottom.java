import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine LEFT-BOTTOM-Switch, d.h. Weiche, die von links kommende
 * Züge im ungestellten Zustand horizontal leitet, im gestellten Zustand nach unten.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class TrackSwitchLeftBottom extends Switch
{    
    /**
     * Erzeugt eine LEFT-BOTTOM-Switch
     */
    public TrackSwitchLeftBottom(String name) {
        super(TrackType.SWITCH_LEFTBOTTOM, "switch_left_bottom_", name);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case RIGHT:
                newDirection = this.isSwitched() ? Direction.BOTTOM : Direction.RIGHT;
                break;
            default:
                newDirection = Direction.LEFT;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case RIGHT: return this.isSwitched() ? Direction.TOP : Direction.LEFT;
            case TOP: return this.isSwitched() ? Direction.LEFT : Direction.BOTTOM;
            case LEFT: return Direction.RIGHT;
            
            default: return Direction.RIGHT;
        }
    }
        
}
