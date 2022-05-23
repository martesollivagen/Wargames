package ntnu.idatt2001.martvaag.model.unit;

import ntnu.idatt2001.martvaag.model.tools.enums.Terrain;

/**
 * class which represent a unit which specialises in range (angrep fra avstand)
 * ranged units have an extra advantage in the terrain hill when attacking, but a disadvantage in the terrain forest
 * the terrain does not affect the resist bonus
 *
 * @version 2022-05-23
 * @author martvaag
 */
public class RangedUnit extends Unit {
    private int hasBeenAttacked = 0;

    /**
     * constructor for a unit with relevant parameters
     *
     * @param name   a short descriptive name, for example "Archer"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor  a defence-value, which protects the unit during an attack
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * simplified constructor with attack = 15 and armor = 8
     * @param name   name of unit
     * @param health health-value of unit
     */
    public RangedUnit(String name, int health){
        super(name, health, 15,8);
    }

    /**
     * bonus added for range-attack, and this value is affected by the terrain
     * bonus is 4 if terrain is hill
     * bonus is 3 if terrain is plains
     * bonus is 2 if terrain is forest
     *
     * @param terrain terrain
     * @return 4,3 or 2, depending on the terrain
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        switch (terrain) {
            case HILL:
                return 4;
            case PLAINS:
                return 3;
            case FOREST:
                return 2;
            default:
                return 0;
        }
    }

    /**
     * bonus added for defence based on the distance from the field, and this value is not affected by the terrain
     * bonus is 6 for the first attack
     * bonus is 4 for the second attack
     * bonus is 2 for the rest of the attacks
     *
     * @param terrain terrain
     * @return 6, 4 og 2, depending on the number of attacks
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        switch (terrain){
            case FOREST :
            case HILL :
            case PLAINS:
                if (hasBeenAttacked == 0){
                    hasBeenAttacked++;
                    return 6;
                } else if (hasBeenAttacked == 1){
                    hasBeenAttacked++;
                    return 4;
                } else return 2;
            default:
                return 0;
        }
    }

    /**
     * method to heal another unit
     * ranged units cannot heal other and the method is therefore empty
     *
     * @param unitOne unit one
     * @param unitTwo unit two
     */
    @Override
    public void heal(Unit unitOne, Unit unitTwo) {

    }
}
