package ntnu.idatt2001.martvaag.model.unit;

import ntnu.idatt2001.martvaag.model.tools.enums.Terrain;

/**
 * class which represent a unit which specialises in melee (nærkamp),
 * and is strong in their first attack (charge)
 * cavalry units have an extra advantage for attack in the terrain plains, and a disadvantage in resist in the terrain forest
 *
 * @version 2022-05-23
 * @author martvaag
 */
public class CavalryUnit extends Unit {
    private int hasAttacked = 0;

    /**
     * constructor for a unit with relevant parameters
     *
     * @param name   a short descriptive name, for example "Swordsman"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor  a defence-value, which protects the unit during an attack
     */
    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health,attack,armor);
    }

    /**
     * simplified constructor with attack = 20 and armor = 12
     *
     * @param name   name of unit
     * @param health health-value of unit
     */
    public CavalryUnit(String name, int health){
        super(name, health, 20, 12);
    }

    /**
     * bonus added for attack, and this value is affected by the terrain
     * bonus is 7 for the first attack and 3 for the rest if the terrain is plains
     * bonus is 6 for the first attack and 2 for the rest if the terrain is forest or plains
     *
     * @param terrain terrain
     * @return 7,6,3 or 2, depending on the terrain and number of attacks
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        switch (terrain){
            case PLAINS:
                if (hasAttacked == 0){
                    hasAttacked++;
                    return 7;
                } else return 3;
            case FOREST:
            case HILL:
                if (hasAttacked == 0){
                    hasAttacked++;
                    return 6;
                } else return 2;
            default:
                return 0;
        }
    }

    /**
     * bonus added for defence, and this value is affected by the terrain
     * bonus is 1 if the terrain is hill or plains
     * bonus is 0 if the terrain is forest
     *
     * @param terrain terrain
     * @return 1 or 0, depending on the terrain
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        switch (terrain){
            case HILL:
            case PLAINS:
                return 1;
            case FOREST:
            default:
                return 0;
        }
    }

    /**
     * method to heal another unit
     * cavalry units cannot heal other and the method is therefore empty
     *
     * @param unitOne unit one
     * @param unitTwo unit two
     */
    @Override
    public void heal(Unit unitOne, Unit unitTwo) {

    }
}
