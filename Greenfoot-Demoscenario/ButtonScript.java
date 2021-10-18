import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import de.wwu.jmrigreenfootinterface.*;

/**
 * A button that lets a specific train execute a custom script.
 * 
 * @author Leonard Bienbeck 
 * @version 1.0.0
 */
public class ButtonScript extends Button
{

    /**
     * The name assigned to the locomotive in the JMRI system.
     */
    private String locomotiveSystemName = "";
    
    /**
     * Creates a button to let a specific train execute a custom script.
     * The train is identified by the given name.
     * 
     * @param locomotiveSystemName The name of the train
     */
    public ButtonScript(String locomotiveSystemName) {
        this.locomotiveSystemName = locomotiveSystemName;
    }

    @Override
    public void onClick() {
        // perform some customized actions
        // ...
    }
    
}
