package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.unit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * class which represent an army consisting of multiple units
 * @version 2022-04-03
 * @author martvaag
 */
public class Army {
    private String name;
    private ArrayList<Unit> units;

    /**
     * constructor for an army with name as parameter
     * @param name name of Army
     */
    public Army(String name) {
        this.name = name;
    }

    /**
     * constructor for army with name and list of units as parameters
     * @param name name of army
     * @param units list of units
     */
    public Army(String name, ArrayList<Unit> units) {
        this.name = name;
        this.units = units;
    }

    /**
     * get the name of the army
     * @return name of the army
     */
    public String getName() {
        return name;
    }

    /**
     * gets all units in list
     * @return list of units
     */
    public ArrayList<Unit> getUnits() {
        return units;
    }

    /**
     * adds a unit in the list of units
     * @param unit unit to be added
     */
    public void add(Unit unit){
        units.add(unit);
    }

    /**
     * adds all units in new list in to the main list of units
     * @param newUnits list of units
     */
    public void addAll(ArrayList<Unit> newUnits){
        units.addAll(newUnits);
    }

    /**
     * removes a unit from the list of units
     * @param unit unit to be removed
     */
    public void remove(Unit unit){
        units.remove(unit);
    }

    /**
     * checks if list of units contains elements
     * @return {@code true} if list contains elements, {@code false} if list is empty
     */
    public boolean hasUnits(){
        return !units.isEmpty();
    }

    /**
     * gets a random unit from list of units
     * @return random unit from list of units
     */
    public Unit getRandom(){
        Random random = new Random();
        int index = random.nextInt(units.size());

        return units.get(index);
    }

    /**
     * a reasonable textual representation og an army
     * @return textual representation of an army
     */
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Unit unit : units){
            result.append("\n").append(unit.toString());
        }
        return "\n" + '"' + name + '"' + "\n" + result;
    }

    /**
     * equals-method so that there are not two identical armies
     * uses the name of the army to check if two are identical
     * @param o object
     * @return {@code true} if two objects are equal, {@code false} if the object does not already exist
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return this.getName().equals(army.getName());
    }

    /**
     * assigns a unique value based on the name of the army
     * @return the hashcode-value of the army
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * get a list of all infantry units in the army
     * @return list of infantry units
     */
    public List<Unit> getInfantryUnits(){
        Predicate<Unit> unitEqualsInfantryUnit = unit -> unit instanceof InfantryUnit;
        return units.stream()
                .filter(unitEqualsInfantryUnit)
                .collect(Collectors.toList());
    }

    /**
     * get a list of all cavalry units, except commander units, in the army
     * @return list of cavalry units
     */
    public List<Unit> getCavalryUnits(){
        Predicate<Unit> unitEqualsCavalryUnit = unit -> unit instanceof CavalryUnit && !(unit instanceof CommanderUnit);
        return units.stream()
                .filter(unitEqualsCavalryUnit)
                .collect(Collectors.toList());
    }

    /**
     * get a list of all ranged units in the army
     * @return list of ranged units
     */
    public List<Unit> getRangedUnits(){
        Predicate<Unit> unitEqualsRangedUnit = unit -> unit instanceof RangedUnit;
        return units.stream()
                .filter(unitEqualsRangedUnit)
                .collect(Collectors.toList());
    }

    /**
     * get a list of all commander units in the army
     * @return list of commander units
     */
    public List<Unit> getCommanderUnits(){
        Predicate<Unit> unitEqualsCommanderUnits = unit -> unit instanceof CommanderUnit;
        return units.stream()
                .filter(unitEqualsCommanderUnits)
                .collect(Collectors.toList());
    }
}