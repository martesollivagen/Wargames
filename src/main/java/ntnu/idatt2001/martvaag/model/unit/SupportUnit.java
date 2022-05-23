package ntnu.idatt2001.martvaag.model.unit;

import ntnu.idatt2001.martvaag.model.tools.enums.Terrain;

/**
 * class which represent a unit with the purpose of healing other units
 * the support units can heal other units when they die in battle
 * the support unit itself has low attack and armor
 * @version 2022-05-22
 * @author martvaag
 */
public class SupportUnit extends Unit{

    /**
     * constructor for a unit with relevant parameters
     *
     * @param name   a short descriptive name, for example "Healer"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor  a defence-value, which protects the unit during an attack
     */
    public SupportUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * simplified constructor with attack = 5 and armor = 5
     *
     * @param name name of unit
     * @param health health-value of unit
     */
    public SupportUnit(String name, int health){
        super(name, health, 5,5);
    }

    /**
     * bonus added for attack
     * @param terrain terrain
     * @return 0
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        return 0;
    }

    /**
     * bonus added for resist
     * @param terrain terrain
     * @return 0
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        return 0;
    }

    /**
     * heal two other units, adds 100 to their health value
     *
     * @param unitOne unit one
     * @param unitTwo unit two
     */
    @Override
    public void heal(Unit unitOne, Unit unitTwo) {
        unitOne.setHealth(unitOne.getHealth() + 100);
        unitTwo.setHealth(unitTwo.getHealth() + 100);
    }
}
