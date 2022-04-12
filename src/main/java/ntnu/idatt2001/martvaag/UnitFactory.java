package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.*;
import java.util.ArrayList;

/**
 * class to create new units
 */
public class UnitFactory {
    /**
     * create a new unit based on type, name and health
     * @param type type of unit
     * @param name name of unit
     * @param health unit's health
     * @return new unit
     */
    public static Unit createUnit(String type, String name, int health){
        if (type.isBlank() || name.isBlank() || health < 0) throw new IllegalArgumentException("Type and name can't be blank and health can't be below 0");
        switch (type.trim().toLowerCase()) {
            case "infantryunit":
                return new InfantryUnit(name, health);
            case "rangedunit":
                return new RangedUnit(name, health);
            case "cavalryunit":
                return new CavalryUnit(name, health);
            case "commanderunit":
                return new CommanderUnit(name, health);
            default:
                return null;
        }
    }

    /**
     * create multiple units based on type, name and healh
     * @param numberOfUnits number of units wanted to be created
     * @param type type of unit
     * @param name name of unit
     * @param health unit's health
     * @return list of units
     */
    public static ArrayList<Unit> createMultipleUnits(int numberOfUnits, String type, String name, int health){
        ArrayList<Unit> units = new ArrayList<>();
        for (int i = 0; i < numberOfUnits; i++){
            units.add(createUnit(type, name, health));
        }

        return units;
    }
}
