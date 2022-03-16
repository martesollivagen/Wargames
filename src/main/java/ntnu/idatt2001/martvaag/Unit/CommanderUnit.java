package ntnu.idatt2001.martvaag.Unit;

import ntnu.idatt2001.martvaag.Unit.CavalryUnit;

/**
 * class which represent a stronger version of a CavalryUnit
 * @version 1.0 2022-03-05
 * @author martvaag
 */
public class CommanderUnit extends CavalryUnit {

    /**
     * constructor for a unit with relevant parameters
     * @param name a short descriptive name, for example "Mountain King"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor a defence-value, which protects the unit during an attack
     */
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * simplified constructor, stronger version of CavalryUnit, with attack = 25 and armor = 15
     * @param name name og unit
     * @param health health-value of unit
     */
    public CommanderUnit(String name, int health){
        super(name, health, 25,15);
    }
}
