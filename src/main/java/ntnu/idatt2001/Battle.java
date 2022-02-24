package ntnu.idatt2001;

import java.util.Random;

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
        Random random = new Random();
        int index1 = random.nextInt(armyOne.getUnits().size());
        int index2 = random.nextInt(armyTwo.getUnits().size());
        //missing code

        return null;
    }

    public String toString(){
        return "The winning army of this battle is: " + simulate();
    }
}
