package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.Unit;

/**
 * class which represent a battle between two armies
 * @version 2022-04-03
 * @author martvaag
 */
public class Battle {
    private Army HumanArmy, OrcishHorde;

    /**
     * constructor to a battle between two armies
     * @param HumanArmy Army 1
     * @param OrcishHorde Army 2
     */
    public Battle(Army HumanArmy, Army OrcishHorde) {
        this.HumanArmy = HumanArmy;
        this.OrcishHorde = OrcishHorde;
    }

    /**
     * simulate a battle between armyOne and armyTwo
     * picks a random unit from one army to attack a random unit from the other army
     * if a unit has a health-value equal to 0, it is removed from the army
     * this process continues until one army has lost all it´s units and the winning army is returned
     * @return the winning army
     */
    public Army simulate(String terrain){
        Army winner;
        int attack = 1;
        while (HumanArmy.hasUnits() && OrcishHorde.hasUnits()){
            Unit humanUnit = HumanArmy.getRandom();
            Unit OrcishUnit = OrcishHorde.getRandom();
            if (attack % 2 == 0){
                HumanArmy.getRandom().attack(OrcishUnit, terrain);
                if (OrcishUnit.isDead()){
                    OrcishHorde.remove(OrcishUnit);
                }
            } else {
                OrcishHorde.getRandom().attack(humanUnit, terrain);
                if (humanUnit.isDead()){
                    HumanArmy.remove(humanUnit);
                }
            }
            attack++;
        }
        if (!HumanArmy.hasUnits()){
            winner = OrcishHorde;
        } else {
            winner = HumanArmy;
        }
        return winner;
    }

    /**
     * get a reasonable textual representation of a battle
     * @return textual representation of a battle
     */
    public String toString(){
        return "The winning army of this battle is: ";
    }
}
