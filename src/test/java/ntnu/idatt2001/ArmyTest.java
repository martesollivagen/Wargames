package ntnu.idatt2001;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for Army class
 * @version 1.0 2022-03-05
 * @author martvaag
 */
class ArmyTest {

    /**
     * test to check if a unit can be added to a list
     */
    @Test
    void addUnitToList() {
        Unit unit = new CavalryUnit("Knight", 100);
        ArrayList<Unit> units = new ArrayList<>();
        assertTrue(units.add(unit));
    }

    /**
     * test to check if a list of units can be added to another list
     */
    @Test
    void addListOfUnitsToOtherList() {
        ArrayList<Unit> units = new ArrayList<>();
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        Unit unit3 = new CavalryUnit("Knight", 100);
        ArrayList<Unit> addedUnits = new ArrayList<>();
        addedUnits.add(unit1);
        addedUnits.add(unit2);
        addedUnits.add(unit3);
        assertTrue(units.addAll(addedUnits));
    }

    /**
     * test to check if a unit can be removed from a list
     */
    @Test
    void removeUnitFromList() {
        Unit unit = new InfantryUnit("Footman", 100);
        ArrayList<Unit> units = new ArrayList<>();
        units.add(unit);
        assertTrue(units.remove(unit));
    }

    /**
     * test to check if an army contains units
     */
    @Test
    public void ArmyHasUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        Army army = new Army("army", units);
        Unit unit = new InfantryUnit("Footman", 100);
        units.add(unit);
        assertTrue(army.hasUnits());
    }

    /**
     * test to check an army with no units
     */
    @Test
    public void ArmyHasNoUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        Army army = new Army("army", units);
        assertFalse(army.hasUnits());
    }

    /**
     * test to check if getRandom-method returns a random unit from the army
     */
    @Test
    void getRandomUnitFromArmy() {
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        Unit unit3 = new CavalryUnit("Knight", 100);
        ArrayList<Unit> units = new ArrayList<>();
        Army army = new Army("army", units);
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        Unit randomUnit = army.getRandom();
        assertTrue(army.getUnits().contains(randomUnit));
    }

    /**
     * test to check equals-method by name of army
     */
    @Test
    void testEqualNames() {
        Army armyOne = new Army("army");
        Army armyTwo = new Army("army");
        assertTrue(armyOne.equals(armyTwo));
    }

    /**
     * test to check equals-method by name of army
     */
    @Test
    void testNotEqualNames() {
        Army armyOne = new Army("armyOne");
        Army armyTwo = new Army("armyTwo");
        assertFalse(armyOne.equals(armyTwo));
    }
}