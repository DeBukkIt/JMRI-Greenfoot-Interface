import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.net.ConnectException;

/**
 * Repräsentiert eine Gleis-Kurve
 * 
 * @author Leonard Bienbeck 
 * @version 1.0.0
 */
public class Curve extends Track
{
    
    public Curve(CurveType curveType, String layoutBlockId) {
        super(curveType.getImageBaseName(), layoutBlockId);
    }
        
    public Curve(CurveType curveType) {
        super(curveType.getImageBaseName());
    }
    
    public void updateImage() {
        String activationModifierString = super.isBlockActive() ? "1" : "0";
        this.setImage(this.imageBaseName + activationModifierString + ".png");
    }    
}
