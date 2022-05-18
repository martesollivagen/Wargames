package ntnu.idatt2001.martvaag.battle;

import ntnu.idatt2001.martvaag.tools.observer.Subject;
import ntnu.idatt2001.martvaag.tools.enums.Terrain;
import ntnu.idatt2001.martvaag.unit.SupportUnit;
import ntnu.idatt2001.martvaag.unit.Unit;

import java.util.Locale;

/**
 * class which represent a battle between two armies
 * @version 2022-05-18
 * @author  martvaag
 */
public class Battle extends Subject {
    private final Army HumanArmy;
    private final Army OrcishHorde;
    private Army winner;
    private Terrain terrainOfBattle;

    /**
     * constructor to a battle between two armies
     * @param HumanArmy   Army 1
     * @param OrcishHorde Army 2
     */
    public Battle(Army HumanArmy, Army OrcishHorde) {
        if (HumanArmy == null || OrcishHorde == null) throw new IllegalArgumentException("The armies cannot be null");
        this.HumanArmy = HumanArmy;
        this.OrcishHorde = OrcishHorde;
    }

    /**
     * get winner of battle
     * @return winner
     */
    public Army getWinner() {
        return winner;
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
     * picks a random unit from one army to attack a random unit from the other army
     * if a unit has a health-value equal to 0, it is removed from the army and observes will get notified on the army and which unit died
     * if the unit that is removed from the army is a SupportUnit, it will heal a random unit from its army
     * this process continues until one army has lost all it´s units and the winning army is returned
     * @return the winning army
     */
    public Army simulate(Terrain terrain){
        terrainOfBattle = terrain;
        if (terrain == null) throw new IllegalArgumentException("Invalid terrain");
        switch (terrain) {
            case HILL:
            case FOREST:
            case PLAINS:
                int attack = 1;
                while (HumanArmy.hasUnits() && OrcishHorde.hasUnits()){
                    Unit humanUnit = HumanArmy.getRandom();
                    Unit OrcishUnit = OrcishHorde.getRandom();
                    if (attack % 2 == 0){
                        HumanArmy.getRandom().attack(OrcishUnit, terrain);
                        if (OrcishUnit.isDead()){
                            if (OrcishUnit instanceof SupportUnit){
                                OrcishUnit.heal(OrcishHorde.getRandom());
                            }
                            OrcishHorde.remove(OrcishUnit);
                            notifyObservers(OrcishHorde,OrcishUnit);
                        }
                    } else {
                        OrcishHorde.getRandom().attack(humanUnit, terrain);
                        if (humanUnit.isDead()){
                            if (humanUnit instanceof SupportUnit){
                                humanUnit.heal(HumanArmy.getRandom());
                            }
                            HumanArmy.remove(humanUnit);
                            notifyObservers(HumanArmy,humanUnit);
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
            default:
                throw new IllegalArgumentException("Invalid terrain");
        }
    }

    /**
     * get a reasonable textual representation of a battle
     * @return textual representation of a battle
     */
    public String toString(){
        return "The winning army of this " + getTerrainOfBattle() + " battle is: " + winner;
    }
}
