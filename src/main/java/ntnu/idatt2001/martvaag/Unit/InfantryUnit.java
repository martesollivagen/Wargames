package ntnu.idatt2001.martvaag.Unit;

/**
 * class which represent a unit which specialises in melee (nærkamp)
 * @version 2022-04-03
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
    public int getAttackBonus(String terrain) {
        if (terrain.equals("FOREST")){
            return 3;
        } else if (terrain.equals("HILL") || terrain.equals("PLAINS")){
            return 2;
        }
        return 0;
    }

    /**
     * bonus added for defence
     * @return bonus = 1
     */
    @Override
    public int getResistBonus(String terrain) {
        if (terrain.equals("FOREST")){
            return 2;
        }
        return 1;
    }
}
