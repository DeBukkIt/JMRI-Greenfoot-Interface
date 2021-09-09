import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert ein Gleis, das vertikal verläuft, d.h. eine bestimmte Art von Track.
 * 
 * @author Leonard Bienbeck 
 * @version 1.0.0
 */
public class TrackVertical extends Track
{
     /**
     * Erzeugt ein Gleis, das vertikal verläuft.
     */
    public TrackVertical() {
        super(TrackType.VERTICAL);
    }
    
    public Track determineNextTrack(Direction movingDirection) {
        return this.getNeighbourAt(movingDirection);
    }
    
    public Direction getReversedDirection(Direction movingDirection) {
        switch(movingDirection) {
            case TOP: return Direction.BOTTOM;
            case BOTTOM: return Direction.TOP;
            default: return Direction.BOTTOM;
        }
    }
    
}
