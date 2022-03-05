package ntnu.idatt2001;

/**
 * class which represent a stronger version of a CavalryUnit
 * @version 1.0 2022-03-05
 * @author martvaag
 */
public class CommanderUnit extends CavalryUnit{
    /**
     * simplified constructor, stronger version of CavalryUnit, with attack = 25 and armor = 15
     * @param name name og unit
     * @param health health-value of unit
     */
    public CommanderUnit(String name, int health){
        super(name, health, 25,15);
    }
}
