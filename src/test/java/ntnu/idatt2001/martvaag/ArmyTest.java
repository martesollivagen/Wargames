package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.*;
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
        assertEquals(armyOne, armyTwo);
    }

    /**
     * test to check equals-method by name of army
     */
    @Test
    void testNotEqualNames() {
        Army armyOne = new Army("armyOne");
        Army armyTwo = new Army("armyTwo");
        assertNotEquals(armyOne, armyTwo);
    }

    @Test
    void testGetInfantryUnits(){
        Army armyOne = new Army("armyOne", addUnits());
        Predicate<Unit> isInfantryUnit = unit -> unit instanceof InfantryUnit;
        boolean check = armyOne.getInfantryUnits().stream().allMatch(isInfantryUnit);

        assertTrue(check);
    }

    @Test
    void testGetCavalryUnits(){
        Army armyOne = new Army("armyOne", addUnits());
        Predicate<Unit> isCavalryUnit = unit -> unit instanceof CavalryUnit;
        boolean check = armyOne.getCavalryUnits().stream().allMatch(isCavalryUnit);

        assertTrue(check);
    }

    @Test
    void testGetRangedUnits(){
        Army armyOne = new Army("armyOne", addUnits());
        Predicate<Unit> isRangedUnit = unit -> unit instanceof RangedUnit;
        boolean check = armyOne.getRangedUnits().stream().allMatch(isRangedUnit);

        assertTrue(check);
    }

    @Test
    void testGetCommanderUnits(){
        Army armyOne = new Army("armyOne", addUnits());
        Predicate<Unit> isCommanderUnit = unit -> unit instanceof CommanderUnit;
        boolean check = armyOne.getCommanderUnits().stream().allMatch(isCommanderUnit);

        assertTrue(check);
    }

    @Test
    void testWriteToFile(){
        Army army = new Army("Marte army", addUnits());
        Army.writeToFile(new File("src/test/file.txt"), army);
    }

    @Test
    void testReadFile(){
        Army.readFromFile(new File("src/test/file.txt"));
    }
}