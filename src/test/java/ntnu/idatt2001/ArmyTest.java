package ntnu.idatt2001;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    @Test
    void add() {
        Unit unit = new CavalryUnit("Knight", 100);
        ArrayList<Unit> units = new ArrayList<>();
        assertTrue(units.add(unit));
    }

    @Test
    void addAll() {
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

    @Test
    void remove() {
        Unit unit = new InfantryUnit("Footman", 100);
        ArrayList<Unit> units = new ArrayList<>();
        units.add(unit);
        assertTrue(units.remove(unit));
    }

    @Test
    public void hasUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        Army army = new Army("army", units);
        Unit unit = new InfantryUnit("Footman", 100);
        units.add(unit);
        assertTrue(army.hasUnits());
    }

    @Test
    public void hasNotUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        Army army = new Army("army", units);
        assertFalse(army.hasUnits());
    }

    @Test
    void getRandom() {
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        Unit unit3 = new CavalryUnit("Knight", 100);
        ArrayList<Unit> units = new ArrayList<>();
        Army army = new Army("army", units);
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        army.getRandom();
        //add
    }

    @Test
    void testEquals() {
        Army armyOne = new Army("army");
        Army armyTwo = new Army("army");
        assertTrue(armyOne.equals(armyTwo));
    }

    @Test
    void testNotEquals() {
        Army armyOne = new Army("armyOne");
        Army armyTwo = new Army("armyTwo");
        assertFalse(armyOne.equals(armyTwo));
    }
}