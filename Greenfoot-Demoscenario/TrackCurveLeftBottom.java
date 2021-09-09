import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine Gleis-Kurve, die LEFT und BOTTOM verbindet, d.h. eine bestimmte Art von Track.
 * 
 * @author Leonard Bienbeck 
 * @version 1.0.0
 */
public class TrackCurveLeftBottom extends Track
{
    /** 
     * Erzeugt eine neue Gleis-Kurve, die LEFT und BOTTOM verbindet.
     */
    public TrackCurveLeftBottom() {
        super(TrackType.CURVE_LEFTBOTTOM);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case RIGHT:
                newDirection = Direction.BOTTOM;
                break;
            case TOP:
                newDirection = Direction.LEFT;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case RIGHT: return Direction.TOP;
            case TOP: return Direction.RIGHT;
            default: return Direction.TOP;
        }
    }
    
}
