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

        for(int i = 0; i<100; i++){
            units.add(unit11);
        }for(int i = 0; i<50; i++){
            units.add(unit12);
        }for(int i = 0; i<20; i++){
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
    public void ArmyHasUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        Army army = new Army("army", units);
        Unit unit = new InfantryUnit("Footman", 100);
        units.add(unit);
        assertTrue(army.hasUnits());
    }

    @Test
    @DisplayName("Army does not have any units")
    public void ArmyHasNoUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        Army army = new Army("army", units);
        assertFalse(army.hasUnits());
    }

    @Test
    @DisplayName("Get random unit from army")
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

    @Test
    @DisplayName("Army equals another army, check same name equals true")
    void testEqualNames() {
        Army armyOne = new Army("army");
        Army armyTwo = new Army("army");
        assertEquals(armyOne, armyTwo);
    }

    @Test
    @DisplayName("Army equals another army, check different names equals false")
    void testNotEqualNames() {
        Army armyOne = new Army("armyOne");
        Army armyTwo = new Army("armyTwo");
        assertNotEquals(armyOne, armyTwo);
    }

    @Test
    @DisplayName("List of InfantryUnits")
    void testGetInfantryUnits(){
        Army armyOne = new Army("armyOne", addUnits());
        Predicate<Unit> isInfantryUnit = unit -> unit instanceof InfantryUnit;
        boolean check = armyOne.getInfantryUnits().stream().allMatch(isInfantryUnit);

        assertTrue(check);
    }

    @Test
    @DisplayName("List of CavalryUnits")
    void testGetCavalryUnits(){
        Army armyOne = new Army("armyOne", addUnits());
        Predicate<Unit> isCavalryUnit = unit -> unit instanceof CavalryUnit;
        boolean check = armyOne.getCavalryUnits().stream().allMatch(isCavalryUnit);

        assertTrue(check);
    }

    @Test
    @DisplayName("List of RangedUnits")
    void testGetRangedUnits(){
        Army armyOne = new Army("armyOne", addUnits());
        Predicate<Unit> isRangedUnit = unit -> unit instanceof RangedUnit;
        boolean check = armyOne.getRangedUnits().stream().allMatch(isRangedUnit);

        assertTrue(check);
    }

    @Test
    @DisplayName("List of CommanderUnits")
    void testGetCommanderUnits(){
        Army armyOne = new Army("armyOne", addUnits());
        Predicate<Unit> isCommanderUnit = unit -> unit instanceof CommanderUnit;
        boolean check = armyOne.getCommanderUnits().stream().allMatch(isCommanderUnit);

        assertTrue(check);
    }

    @Test
    @DisplayName("Write an army to a file")
    void testWriteToFile(){
        Army army = new Army("Marte army", addUnits());
        Army.writeToFile(new File("src/test/file.txt"), army);
    }

    @Test
    @DisplayName("Read a file")
    void testReadFile(){
        Army.readFromFile(new File("src/test/file.txt"));
    }
}