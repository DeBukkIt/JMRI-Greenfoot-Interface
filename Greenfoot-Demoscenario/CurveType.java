/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse CurveType.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public enum CurveType  
{
    
    TOP_LEFT("panel_track_curve_top_left_"), TOP_RIGHT("panel_track_curve_top_right_"), BOTTOM_LEFT("panel_track_curve_bottom_left_"), BOTTOM_RIGHT("panel_track_curve_bottom_right_");
    
    private String imageBaseName;
    
    CurveType(String imageBaseName) {
        this.imageBaseName = imageBaseName;
    }
    
    public String getImageBaseName() {
        return this.imageBaseName;
    }
    
}
