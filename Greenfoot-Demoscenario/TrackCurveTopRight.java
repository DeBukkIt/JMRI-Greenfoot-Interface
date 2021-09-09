import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert eine Gleis-Kurve, die TOP und RIGHT verbindet, d.h. eine bestimmte Art von Track.
 * 
 * @author Leonard Bienbeck 
 * @version 1.0.0
 */
public class TrackCurveTopRight extends Track
{
    /**
     * Erzeugt eine neue Gleis-Kurve, die TOP und RIGHT verbindet.
     */
    public TrackCurveTopRight() {
        super(TrackType.CURVE_TOPRIGHT);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        Direction newDirection = null;
        switch(movingDirection) {
            case BOTTOM:
                newDirection = Direction.RIGHT;
                break;
            case LEFT:
                newDirection = Direction.TOP;
                break;
        }
        return this.getNeighbourAt(newDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case LEFT: return Direction.BOTTOM;
            case BOTTOM: return Direction.LEFT;
            default: return Direction.BOTTOM;
        }
    }
    
}
