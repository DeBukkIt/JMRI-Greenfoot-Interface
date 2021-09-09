import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert ein Gleis, das horizontal verläuft, d.h. eine bestimmte Art von Track.
 * 
 * @author Leonard Bienbeck 
 * @version 1.0.0
 */
public class TrackHorizontal extends Track
{
    /**
     * Erzeugt ein Gleis, das horizontal verläuft.
     */
    public TrackHorizontal() {
        super(TrackType.HORIZONTAL);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        return this.getNeighbourAt(movingDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case LEFT: return Direction.RIGHT;
            case RIGHT: return Direction.LEFT;
            default: return Direction.RIGHT;
        }
    }
    
}