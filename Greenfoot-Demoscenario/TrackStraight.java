import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Repräsentiert ein Gleis, das horizontal verläuft
 * 
 * @author Leonard Bienbeck 
 * @version 1.0.0
 */
public class TrackStraight extends Track
{    
    public TrackStraight(StraightType straightType, String layoutBlockId) {
        super(straightType.getImageBaseName(), layoutBlockId);
    }
        
    public TrackStraight(StraightType straightType) {
        super(straightType.getImageBaseName());
    }
    
    public void updateImage() {
        String activationModifierString = super.isBlockActive() ? "1" : "0";
        this.setImage(this.imageBaseName + activationModifierString + ".png");
    }    
}