import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Eine Welt, in der das Schema einer Modelleisenbahn (Panel) nachgebildet werden kann.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class PanelWorld extends World
{

    /**
     * Konstruktor für Objekte der Klasse PanelWorld
     * 
     */
    public PanelWorld()
    {    
        // Create a new world with 16x12 cells with a cell size of 64x64 pixels.
        super(16, 12, 64);

        paintBackgroundGrid();
        prepare();
    }
    
    public void act() {        
        // Clicks on turnouts
        if(Greenfoot.getMouseInfo() != null) {
            Actor focusedActor = Greenfoot.getMouseInfo().getActor();
            if(Greenfoot.mousePressed(focusedActor)) {
                if(focusedActor != null && focusedActor instanceof Turnout) {
                    Switch clickedSwitch = (Switch) focusedActor;
                    clickedSwitch.toggleSwitched();
                }
            }
        }
    }
    
    private void paintBackgroundGrid() {
        GreenfootImage bg = getBackground();

        bg.setColor(Color.GRAY);
        for(int x = 1; x < getWidth(); x++) {
            bg.drawLine(x * getCellSize(), 0, x * getCellSize(), getHeight() * getCellSize());
        }
        for(int y = 1; y < getWidth(); y++) {
            bg.drawLine(0, y * getCellSize(), getWidth() * getCellSize(), y * getCellSize());
        }
    }
    
    private void prepare() {

    }
       
}