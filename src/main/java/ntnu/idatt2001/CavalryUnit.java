package ntnu.idatt2001;
/**
 * class which represent a unit which specialises in melee (nærkamp),
 * and is strong in their first attack (charge)
 * @version 1.0 2022-02-24
 * @author martvaag
 */
public class CavalryUnit extends Unit{

    /**
     * constructor for a unit with relevant parameters
     * @param name a short descriptive name, for example "Swordsman" or "Archer"
     * @param health a value which indicates the unit´s health, The value reduces when the unit is attacked,
     *               and can never be below 0
     * @param attack an attack-value, which represents the unit´s weapons
     * @param armor a defence-value, which protects the unit during an attack
     */
    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health,attack,armor);
    }

    /**
     * simplified constructor with attack = 20 and armor = 12
     * @param name name of unit
     * @param health health-value of unit
     */
    public CavalryUnit(String name, int health){
        super(name, health, 20, 12);
    }

    /**
     * bonus added for attack
     * @return 4+2 for the first attack, then 2 for the rest
     */
    @Override
    public int getAttackbonus() {
        //missing code
        return 0;
    }

    /**
     * bonus added for defence, but small advantage when attacked in melee
     * @return bonus = 1
     */
    @Override
    public int getResistBonus() {
        return 1;
    }
}
