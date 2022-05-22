package ntnu.idatt2001.martvaag.battle;

import ntnu.idatt2001.martvaag.tools.observer.Subject;
import ntnu.idatt2001.martvaag.tools.enums.Terrain;
import ntnu.idatt2001.martvaag.unit.SupportUnit;
import ntnu.idatt2001.martvaag.unit.Unit;

import java.util.Locale;

/**
 * class which represent a battle between two armies
 * @version 2022-05-22
 * @author  martvaag
 */
public class Battle extends Subject {
    private final Army armyOne, armyTwo;
    private Army winner;
    private Terrain terrainOfBattle;

    /**
     * constructor to a battle between two armies
     *
     * @param armyOne Army one
     * @param armyTwo Army two
     */
    public Battle(Army armyOne, Army armyTwo) {
        if (armyOne == null || armyTwo == null) throw new IllegalArgumentException("The armies cannot be null");
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * get winner of battle
     * @return winner
     */
    public Army getWinner() {
        return winner;
    }

    /**
     * get army one
     * @return army one
     */
    public Army armyOne(){
        return armyOne;
    }

    /**
     * get army two
     * @return army two
     */
    public Army armyTwo(){
        return armyTwo;
    }

    /**
     * get terrain of battle
     * @return terrain
     */
    public String getTerrainOfBattle() {
        return terrainOfBattle.toString().toLowerCase(Locale.ROOT);
    }

    /**
     * simulate a battle between armyOne and armyTwo
     * uses the fight method, and returns the winner of the battle
     *
     * @param terrain terrain
     * @return the winning army
     */
    public Army simulate(Terrain terrain){
        terrainOfBattle = terrain;
        if (terrain == null) throw new IllegalArgumentException("Invalid terrain");
        switch (terrain) {
            case HILL:
            case FOREST:
            case PLAINS:
                fight(terrain);
                if (!armyOne.hasUnits()){
                    winner = armyTwo;
                } else {
                    winner = armyOne;
                }
                return winner;
            default:
                return null;
        }
    }

    /**
     * picks a random unit from one army to attack a random unit from the other army
     * if a unit has a health-value equal to 0, it is removed from the army and observes will get notified on the army and which unit died
     * if the unit that is removed from the army is a SupportUnit, it will heal a random unit from its own army
     * this process continues until one army has lost all it´s units
     *
     * @param terrain terrain
     */
    public void fight(Terrain terrain){
        int attack = 1;
        while (armyOne.hasUnits() && armyTwo.hasUnits()){
            Unit armyOneUnit = armyOne.getRandom();
            Unit armyTwoUnit = armyTwo.getRandom();
            if (attack % 2 == 0){
                armyOne.getRandom().attack(armyTwoUnit, terrain);
                if (armyTwoUnit.isDead()){
                    if (armyTwoUnit instanceof SupportUnit){
                        armyTwoUnit.heal(armyTwo.getRandom());
                    }
                    armyTwo.remove(armyTwoUnit);
                    notifyObservers(armyTwo,armyTwoUnit);
                }
            } else {
                armyTwo.getRandom().attack(armyOneUnit, terrain);
                if (armyOneUnit.isDead()){
                    if (armyOneUnit instanceof SupportUnit){
                        armyOneUnit.heal(armyOne.getRandom());
                    }
                    armyOne.remove(armyOneUnit);
                    notifyObservers(armyOne,armyOneUnit);
                }
            }
            attack++;
        }
    }

    /**
     * get a reasonable textual representation of a battle
     * @return textual representation of a battle
     */
    public String toString(){
        return "The winning army of this " + getTerrainOfBattle() + " battle is:\n" + getWinner();
    }
}