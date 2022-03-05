package ntnu.idatt2001;

/**
 * class which represent a battle between two armies
 * @version 1.0 2022-02-24
 * @author martvaag
 */
public class Battle {
    private Army armyOne, armyTwo;

    /**
     * constructor to a battle between two armies
     * @param armyOne Army 1
     * @param armyTwo Army 2
     */
    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * simulate a battle between armyOne and armyTwo
     * picks a random unit from one army to attack a random unit from the other army
     * if a unit has a health-value equal to 0, it is removed from the army
     * this process continues until one army has lost all it´s units and the winning army is returned
     * @return the winning army
     */
    public Army simulate(){
        Army winner;
        int attack = 1;
        while (armyOne.hasUnits() && armyTwo.hasUnits()){
            Unit one = armyOne.getRandom();
            Unit two = armyTwo.getRandom();
            if (attack % 2 == 0){
                armyOne.getRandom().attack(two);
                if (two.isDead()){
                    armyTwo.remove(two);
                }
            } else {
                armyTwo.getRandom().attack(one);
                if (one.isDead()){
                    armyOne.remove(one);
                }
            }
            attack++;
        }
        if (!armyOne.hasUnits()){
            winner = armyTwo;
        } else {
            winner = armyOne;
        }
        return winner;
    }

    /**
     * get a reasonable textual representation of a battle
     * @return textual representation of a battle
     */
    public String toString(){
        return "The winning army of this battle is: " + simulate();
    }
}
