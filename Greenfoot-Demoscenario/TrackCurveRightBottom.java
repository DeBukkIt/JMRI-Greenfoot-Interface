import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine Gleis-Kurve, die RIGHT und BOTTOM verbindet, d.h. eine bestimmte Art von Track.
 * 
 * @author Leonard Bienbeck 
 * @version 1.0.0
 */
public class TrackCurveRightBottom extends Track
{
    /**
     * Erzeugt eine neue Gleis-Kurve, die RIGHT und BOTTOM verbindet.
     */
    public TrackCurveRightBottom() {
        super(TrackType.CURVE_RIGHTBOTTOM);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case LEFT:
                newDirection = Direction.BOTTOM;
                break;
            case TOP:
                newDirection = Direction.RIGHT;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case TOP: return Direction.LEFT;
            case LEFT: return Direction.TOP;
            default: return Direction.LEFT;
        }
    }
}
