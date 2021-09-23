/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse CurveType.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public enum StraightType  
{
    
    HORIZONTAL("panel_track_horizontal_"), VERTICAL("panel_track_vertical_");
    
    private String imageBaseName;
    
    StraightType(String imageBaseName) {
        this.imageBaseName = imageBaseName;
    }
    
    public String getImageBaseName() {
        return this.imageBaseName;
    }
    
}
