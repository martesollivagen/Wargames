package ntnu.idatt2001.martvaag.Unit;

import ntnu.idatt2001.martvaag.Unit.Unit;

/**
 * class which represent a unit which specialises in range (angrep fra avstand)
 * @version 1.0 2022-03-05
 * @author martvaag
 */
public class RangedUnit extends Unit {
    private int hasBeenAttacked = 0;

    /**
     * constructor for a unit with relevant parameters
     * @param name a short descriptive name, for example "Archer"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor a defence-value, which protects the unit during an attack
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * simplified constructor with attack = 15 and armor = 8
     * @param name name of unit
     * @param health health-value of unit
     */
    public RangedUnit(String name, int health){
        super(name, health, 15,8);
    }

    /**
     * bonus added for range-attack
     * @return bonus = 3
     */
    @Override
    public int getAttackBonus() {
        return 3;
    }

    /**
     * bonus added for defence based on the distance from the field
     * when attacked the first time - return 6
     * when attacked the second time - return 4
     * from the third attack and so on - return 2
     * @return 6, 4 og 2, depending on the number of attacks
     */
    @Override
    public int getResistBonus() {
        if (hasBeenAttacked == 0){
            hasBeenAttacked++;
            return 6;
        } else if (hasBeenAttacked == 1){
            hasBeenAttacked++;
            return 4;
        } else return 2;
    }
}
