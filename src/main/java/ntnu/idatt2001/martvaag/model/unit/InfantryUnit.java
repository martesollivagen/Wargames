package ntnu.idatt2001.martvaag.model.unit;

import ntnu.idatt2001.martvaag.model.tools.enums.Terrain;

/**
 * class which represent a unit which specialises in melee (nærkamp)
 * infantry units have an extra advantage in the terrain forest, both in attack and defence
 *
 * @version 2022-05-23
 * @author martvaag
 */
public class InfantryUnit extends Unit {

    /**
     * constructor for a unit with relevant parameters
     *
     * @param name   a short descriptive name, for example "Footman"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor  a defence-value, which protects the unit during an attack
     */
    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * simplified constructor with attack = 15 and armor = 10
     *
     * @param name   name of unit
     * @param health health-value of unit
     */
    public InfantryUnit(String name, int health){
        super(name, health,15,10);
    }

    /**
     * bonus added for melee-attack, and this value is affected by the terrain
     * bonus is 3 if terrain is forrest
     * bonus is 2 if terrain is hill or plains
     *
     * @param terrain terrain
     * @return 3 or 2, depending on the terrain
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        switch (terrain){
            case FOREST:
                return 3;
            case HILL:
            case PLAINS:
                return 2;
            default:
                return 0;
        }
    }

    /**
     * bonus added for defence, and this value is affected by the terrain
     * bonus is 2 if terrain is forest
     * bonus is 1 if terrain is hill or plains
     *
     * @param terrain terrain
     * @return 2 or 1, depending on the terrain
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        switch (terrain){
            case FOREST:
                return 2;
            case HILL:
            case PLAINS:
                return 1;
            default:
                return 0;
        }
    }

    /**
     * method to heal another unit
     * infantry units cannot heal other and the method is therefore empty
     *
     * @param unitOne unit one
     * @param unitTwo unit two
     */
    @Override
    public void heal(Unit unitOne, Unit unitTwo) {

    }
}
