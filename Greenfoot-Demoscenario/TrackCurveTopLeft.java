import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine Gleis-Kurve, die TOP und LEFT verbindet, d.h. eine bestimmte Art von Track.
 * 
 * @author Leonard Bienbeck 
 * @version 1.0.0
 */
public class TrackCurveTopLeft extends Track
{
    /**
     * Erzeugt eine neue Gleis-Kurve, die TOP und LEFT verbindet.
     */
    public TrackCurveTopLeft() {
        super(TrackType.CURVE_TOPLEFT);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case RIGHT:
                newDirection = Direction.TOP;
                break;
            case BOTTOM:
                newDirection = Direction.LEFT;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case RIGHT: return Direction.BOTTOM;
            case BOTTOM: return Direction.RIGHT;
            default: return Direction.BOTTOM;
        }
    }
    
}
