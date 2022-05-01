package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for Army class
 * @version 2022-04-03
 * @author martvaag
 */
class ArmyTest {

    public ArrayList<Unit> addUnits(){

        ArrayList<Unit> units = new ArrayList<>();

        units.addAll(UnitFactory.createMultipleUnits(10,"infantryunit","Footman",100));
        units.addAll(UnitFactory.createMultipleUnits(5,"cavalryunit","Knight",100));
        units.addAll(UnitFactory.createMultipleUnits(2,"rangedunit","Archer",100));
        units.addAll(UnitFactory.createMultipleUnits(1,"commanderunit","Mountain King",100));

        return units;
    }

    @Test
    @DisplayName("Add a unit to a list")
    void addUnitToList() {
        Unit unit = new CavalryUnit("Knight", 100);
        ArrayList<Unit> units = new ArrayList<>();
        assertTrue(units.add(unit));
    }

    @Test
    @DisplayName("Add a list of units to another list")
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

    @Test
    @DisplayName("Remove a unit from a list")
    void removeUnitFromList() {
        Unit unit = new InfantryUnit("Footman", 100);
        ArrayList<Unit> units = new ArrayList<>();
        units.add(unit);
        assertTrue(units.remove(unit));
    }

    @Test
    @DisplayName("Army contains units")
    void armyHasUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        Army humanArmy = new Army("Human army", units);
        Unit unit = new InfantryUnit("Footman", 100);
        units.add(unit);
        assertTrue(humanArmy.hasUnits());
    }

    @Test
    @DisplayName("Army does not have any units")
    void armyHasNoUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        Army humanArmy = new Army("Human army", units);
        assertFalse(humanArmy.hasUnits());
    }

    @Test
    @DisplayName("Get random unit from army")
    void getRandomUnitFromArmy() {
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        Unit unit3 = new CavalryUnit("Knight", 100);
        ArrayList<Unit> units = new ArrayList<>();
        Army humanArmy = new Army("Human army", units);
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        Unit randomUnit = humanArmy.getRandom();
        assertTrue(humanArmy.getUnits().contains(randomUnit));
    }

    @Test
    @DisplayName("Army equals another army, check same name equals true")
    void equalNames() {
        Army humanArmy1 = new Army("Human army");
        Army humanArmy2 = new Army("Human army");
        assertEquals(humanArmy1, humanArmy2);
    }

    @Test
    @DisplayName("Army equals another army, check different names equals false")
    void notEqualNames() {
        Army humanArmy = new Army("Human Army");
        Army orcishHorde = new Army("Orcish Horde");
        assertNotEquals(humanArmy, orcishHorde);
    }

    @Test
    @DisplayName("List of InfantryUnits")
    void getInfantryUnits(){
        Army humanArmy = new Army("Human army", addUnits());
        Predicate<Unit> isInfantryUnit = unit -> unit instanceof InfantryUnit;
        boolean check = humanArmy.getInfantryUnits().stream().allMatch(isInfantryUnit);

        assertTrue(check);
    }

    @Test
    @DisplayName("List of CavalryUnits")
    void getCavalryUnits(){
        Army humanArmy = new Army("Human army", addUnits());
        Predicate<Unit> isCavalryUnit = unit -> unit instanceof CavalryUnit && !(unit instanceof CommanderUnit);
        boolean check = humanArmy.getCavalryUnits().stream().allMatch(isCavalryUnit);

        assertTrue(check);
    }

    @Test
    @DisplayName("List of RangedUnits")
    void getRangedUnits(){
        Army humanArmy = new Army("Human army", addUnits());
        Predicate<Unit> isRangedUnit = unit -> unit instanceof RangedUnit;
        boolean check = humanArmy.getRangedUnits().stream().allMatch(isRangedUnit);

        assertTrue(check);
    }

    @Test
    @DisplayName("List of CommanderUnits")
    void getCommanderUnits(){
        Army humanArmy = new Army("Human army", addUnits());
        Predicate<Unit> isCommanderUnit = unit -> unit instanceof CommanderUnit;
        boolean check = humanArmy.getCommanderUnits().stream().allMatch(isCommanderUnit);

        assertTrue(check);
    }
}