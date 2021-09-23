/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse CurveType.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public enum TurnoutType  
{
    
    TOP("panel_track_branch_top_"),
    RIGHT("panel_track_branch_right_"),
    LEFT("panel_track_branch_left_"),
    BOTTOM("panel_track_branch_bottom_");
    
    private String imageBaseName;
    
    TurnoutType(String imageBaseName) {
        this.imageBaseName = imageBaseName;
    }
    
    public String getImageBaseName() {
        return this.imageBaseName;
    }
    
}
