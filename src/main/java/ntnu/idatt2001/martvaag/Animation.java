package ntnu.idatt2001.martvaag;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import ntnu.idatt2001.martvaag.battle.Army;
import ntnu.idatt2001.martvaag.battle.Battle;
import ntnu.idatt2001.martvaag.tools.observer.Observer;
import ntnu.idatt2001.martvaag.tools.enums.Terrain;
import ntnu.idatt2001.martvaag.tools.factory.UnitFactory;
import ntnu.idatt2001.martvaag.unit.Unit;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class to create an animation of the battle
 * implements the Observer interface
 * @version 1.0 2022-05-18
 * @author martvaag
 */
public class Animation implements Observer {

    private final Army armyOne, armyTwo;
    private final Battle battle;
    private final Terrain terrain;
    private final ArrayList<Unit> listOne, listTwo;
    private final ArrayList<Boolean> order;

    /**
     * constructor with parameters to use in the animation
     * @param one army one
     * @param two army two
     * @param terrain terrain
     */
    public Animation(Army one, Army two, Terrain terrain) {
        this.armyOne = new Army(one.getName(), new ArrayList<>(one.getUnits().stream().map(UnitFactory::createUnitCopy).collect(Collectors.toList())));
        this.armyTwo = new Army(two.getName(), new ArrayList<>(two.getUnits().stream().map(UnitFactory::createUnitCopy).collect(Collectors.toList())));
        this.terrain = terrain;
        this.battle = new Battle(createArmyCopy(armyOne), createArmyCopy(armyTwo));
        order = new ArrayList<>();
        listOne = new ArrayList<>();
        listTwo = new ArrayList<>();
    }

    /**
     * get army one
     * @return army one
     */
    public Army getArmyOne() {
        return armyOne;
    }

    /**
     * get army two
     * @return army two
     */
    public Army getArmyTwo() {
        return armyTwo;
    }

    /**
     * get battle
     * @return battle
     */
    public Battle getBattle() {
        return battle;
    }

    /**
     * get terrain of battle
     * @return terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * get list one
     * @return list one
     */
    public ArrayList<Unit> getListOne() {
        return listOne;
    }

    /**
     * get list two
     * @return list two
     */
    public ArrayList<Unit> getListTwo() {
        return listTwo;
    }

    /**
     * get list of order
     * @return list of order
     */
    public ArrayList<Boolean> getOrder() {
        return order;
    }

    /**
     * get the display of all units left in the army
     * @param army army
     * @return display of all units left in the army
     */
    public String getDisplayList(Army army) {
        try {

            return "Army : " + army.getName() +
                    "\n\nTotal number of units:        " + army.getUnits().size() +
                    "\nTotal number of InfantryUnits:  " + army.getInfantryUnits().size() +
                    "\nTotal number of RangedUnits:    " + army.getRangedUnits().size() +
                    "\nTotal number of CavalryUnits:   " + army.getCavalryUnits().size() +
                    "\nTotal number of CommanderUnits: " + army.getCommanderUnits().size() +
                    "\nTotal number of Support units: " + army.getSupportUnits().size();
        } catch (ConcurrentModificationException e){
            return "Army : " + army.getName() +
                    "\n\nTotal number of units:        " + army.getUnits().size() +
                    "\nTotal number of InfantryUnits:  " + army.getInfantryUnits().size() +
                    "\nTotal number of RangedUnits:    " + army.getRangedUnits().size() +
                    "\nTotal number of CavalryUnits:   " + army.getCavalryUnits().size() +
                    "\nTotal number of CommanderUnits: " + army.getCommanderUnits().size() +
                    "\nTotal number of Support units: " + army.getSupportUnits().size();
        }
    }

    /**
     * attach this to the battle in order to get notified when a unit dies
     * simulate a battle between army one and army two
     * detach this to the battle
     */
    public void simulate() {
        battle.attach(this);
        battle.simulate(terrain);
        battle.detach(this);
    }

    /**
     * method that creates animation of the battle
     * removes units from each list and using the order list to know which army to remove a unit from
     * while the animation runs, it uses the getDisplayList method to show number of units left
     * uses the tread.sleep method to run the animation in a reasonable tempo to be able to see all the units being removed
     * @param textAreaArmyOne textArea for army one where animation is displayed
     * @param textAreaArmyTwo textArea for army two where animation is displayed
     * @throws InterruptedException interrupted exception
     */
    public void createAnimation(TextArea textAreaArmyOne, TextArea textAreaArmyTwo) throws InterruptedException {
        simulate();

        Runnable update = () -> {
            textAreaArmyOne.setText(getDisplayList(armyOne));
            textAreaArmyTwo.setText(getDisplayList(armyTwo));
        };

        while ((!listOne.isEmpty() || !listTwo.isEmpty()) && !order.isEmpty()) {
            if (order.get(0)) {
                remove(armyOne, listOne);
            } else {
                remove(armyTwo, listTwo);
            }

            Thread.sleep(50);
            Platform.runLater(update);
        }
    }

    /**
     * remove a unit from a list
     * checks which type of unit and removes the first unit that matches this in the army
     * removes the unit from list of units and removes a boolean from the order list
     * @param army army
     * @param listOfUnits list of units
     */
    public void remove(Army army, List<Unit> listOfUnits) {
        Unit unit = listOfUnits.get(0);

        switch (unit.getClass().getSimpleName()){
            case "InfantryUnit" :
                army.remove(army.getInfantryUnits().stream().findFirst().get());
                break;
            case "RangedUnit" :
                army.remove(army.getRangedUnits().stream().findFirst().get());
                break;
            case "CavalryUnit" :
                army.remove(army.getCavalryUnits().stream().findFirst().get());
                break;
            case "CommanderUnit" :
                army.remove(army.getCommanderUnits().stream().findFirst().get());
                break;
            case "Support" :
                army.remove(army.getSupportUnits().stream().findFirst().get());
                break;
            default:

        }
        listOfUnits.remove(0);
        order.remove(0);
    }

    /**
     * updates the observer (this) every time a unit has died
     * adds the dead unit to a list; listOne if the unit was from armyOne and listTwo if the unit was from armyTwo
     * adds a boolean to the order list, to be used in the animation to remove the units from their lists in the same order as it happened in the battle
     * @param army army
     * @param unit unit
     */
    @Override
    public void update(Army army, Unit unit) {
        if (army.equals(armyOne)) {
            listOne.add(unit);
            order.add(true);
        } else {
            listTwo.add(unit);
            order.add(false);
        }
    }

    /**
     * create a copy of the army to use in the simulation
     * @param army army
     * @return copy of army
     */
    public Army createArmyCopy(Army army) {
        return new Army(army.getName(), new ArrayList<>(army.getUnits().stream().map(UnitFactory::createUnitCopy).collect(Collectors.toList())));
    }
}