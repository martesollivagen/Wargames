package ntnu.idatt2001;

/**
 * class which represent a unit which specialises in melee (nærkamp)
 * @version 1.0 2022-03-05
 * @author martvaag
 */
public class InfantryUnit extends Unit{
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
