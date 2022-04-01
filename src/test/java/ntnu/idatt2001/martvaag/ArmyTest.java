package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for Army class
 * @version 1.0 2022-03-05
 * @author martvaag
 */
class ArmyTest {

    public ArrayList<Unit> addUnits(){
        Unit unit11 = new InfantryUnit("Footman", 100);
        Unit unit12 = new CavalryUnit("Knight", 100);
        Unit unit13 = new RangedUnit("Archer", 100);
        Unit unit14 = new CommanderUnit("Mountain King", 180);

        ArrayList<Unit> units = new ArrayList<>();

        for(int i = 0; i<10; i++){
            units.add(unit11);
        }for(int i = 0; i<5; i++){
            units.add(unit12);
        }for(int i = 0; i<2; i++){
            units.add(unit13);
        }for(int i = 0; i<1; i++){
            units.add(unit14);
        }
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

    @Test
    @DisplayName("Write an army to a file")
    void writeToFile(){
        Army humanArmy = new Army("Human army", addUnits());
        File file = new File("src/main/resources/human-army.csv");
        Army.writeToFile(file, humanArmy);
        assertTrue(file.exists());
        assertTrue(file.canWrite());
        assertTrue(file.length() > 0);
    }

    @Test
    @DisplayName("Read an army from a file")
    void readFile(){
        File file = new File("src/main/resources/human-army.csv");
        Army.readFromFile(file);
        assertTrue(file.exists());
        assertTrue(file.canRead());
    }
}