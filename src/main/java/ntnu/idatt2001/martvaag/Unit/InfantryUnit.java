package ntnu.idatt2001.martvaag.Unit;

import ntnu.idatt2001.martvaag.Unit.Unit;

/**
 * class which represent a unit which specialises in melee (nærkamp)
 * @version 1.0 2022-03-05
 * @author martvaag
 */
public class InfantryUnit extends Unit {

    /**
     * constructor for a unit with relevant parameters
     * @param name a short descriptive name, for example "Footman"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor a defence-value, which protects the unit during an attack
     */
    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * simplified constructor with attack = 15 and armor = 10
     * @param name name of unit
     * @param health health-value of unit
     */
    public InfantryUnit(String name, int health){
        super(name, health,15,10);
    }

    /**
     * bonus added for melee-attack
     * @return bonus = 2
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }

    /**
     * bonus added for defence
     * @return bonus = 1
     */
    @Override
    public int getResistBonus() {
        return 1;
    }
}
