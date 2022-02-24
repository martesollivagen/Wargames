package ntnu.idatt2001;

/**
 * class which represent a unit which specialises in range (angrep fra avstand)
 * @version 1.0 2022-02-24
 * @author martvaag
 */
public class RangedUnit extends Unit{
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
    public int getAttackbonus() {
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
        //missing code
        return 0;
    }
}
