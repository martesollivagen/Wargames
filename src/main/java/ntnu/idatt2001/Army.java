package ntnu.idatt2001;

import java.util.ArrayList;
import java.util.Random;

/**
 * class which represent an army consisting of multiple units
 * @version 1.0 2022-02-24
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
        for(Unit u : newUnits){
            units.add(u);
        }
    }

    /**
     * removes a unit from the list of units
     * @param unit unit wanted removed
     */
    public void remove(Unit unit){
        units.remove(unit);
    }

    /**
     * checks if list of units contains elements
     * @return true if list contains elements, false if list is empty
     */
    public boolean hadUnits(){
        boolean has = false;
        if(!units.isEmpty()){
            has = true;
        }
        return has;
    }

    /**
     * gets a random unit from list of units
     * @return random unit from list of units
     */
    public Unit getRandom(){
        Random random = new Random();
        int index = random.nextInt(units.size());
        for(int i = 0; i<units.size(); i++){
            Unit unit = units.get(index);
            return unit;
        }
        return null;
    }

    /**
     * a reasonable textual representation og an army
     * @return textual representation of an army
     */
    public String toString(){
        String resultat = "";
        for(Unit unit : units){
            resultat += unit.toString();
        }
        return "Army: " + name + "\n" + resultat;
    }

    /**
     * equals-method so that there are not two identical armies
     * uses the name of the army to check if two are identical
     * @param o object
     * @return "true" if two objects are equal, "false" if the object does not already exist
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return this.getName().equals(army.getName());
    }

    //add hashcode
}
