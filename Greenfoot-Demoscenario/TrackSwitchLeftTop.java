import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine LEFT-TOP-Switch, d.h. Weiche, die von links kommende
 * Züge im ungestellten Zustand horizontal leitet, im gestellten Zustand nach oben.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class TrackSwitchLeftTop extends Switch
{    
    /**
     * Erzeugt eine LEFT-TOP-Switch
     */
    public TrackSwitchLeftTop(String name) {
        super(TrackType.SWITCH_LEFTTOP, "switch_left_top_", name);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case RIGHT:
                newDirection = this.isSwitched() ? Direction.TOP : Direction.RIGHT;
                break;
            default:
                newDirection = Direction.LEFT;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case LEFT: return Direction.RIGHT;
            case BOTTOM: return this.isSwitched() ? Direction.RIGHT : Direction.TOP;
            case RIGHT: return this.isSwitched() ? Direction.BOTTOM : Direction.LEFT;
            
            default: return Direction.RIGHT;
        }
    }
        
}
