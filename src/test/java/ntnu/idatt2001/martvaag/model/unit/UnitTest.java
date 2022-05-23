package ntnu.idatt2001.martvaag.model.unit;

import ntnu.idatt2001.martvaag.model.tools.enums.Terrain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for Unit class
 * @version 2022-05-19
 * @author martvaag
 */
public class UnitTest {

    @Test
    @DisplayName("Unit attack another unit, check correct value equals true")
    public void unitAttackAnotherUnitCheckTrueValue() {
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        unit1.attack(unit2,Terrain.HILL);
        assertEquals(97, unit2.getHealth());
    }

    @Test
    @DisplayName("Unit attack another unit, check wrong value equals false")
    public void unitAttackAnotherUnitCheckForFalseValue(){
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        unit1.attack(unit2, Terrain.PLAINS);
        assertNotEquals(100, unit2.getHealth());
    }

    @Test
    @DisplayName("Set unit health, check correct value equals true")
    public void setUnitHealthTo80IsTrue() {
        Unit unit = new InfantryUnit("Footman", 100);
        unit.setHealth(80);
        assertEquals(80, unit.getHealth());
    }

    @Test
    @DisplayName("Set unit health, check wrong value equals false")
    public void setUnitHealthTo80IsFalse(){
        Unit unit = new InfantryUnit("Footman", 100);
        unit.setHealth(80);
        assertNotEquals(100, unit.getHealth());
    }

    @Test
    @DisplayName("Try create unit with invalid health to check thrown exception")
    public void createUnitWithInvalidHealthToThrowException(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Unit unit = new InfantryUnit("Footman",-100);
        }, "Unit's health cannot be below 0");
        assertEquals("Unit's health cannot be below 0", thrown.getMessage());
    }

    @Test
    @DisplayName("Try create unit with empty name to check thrown exception")
    public void createUnitWithEmptyNameToThrowException(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Unit unit = new InfantryUnit("",100);
        }, "Unit's name cannot be empty");
        assertEquals("Unit's name cannot be empty", thrown.getMessage());
    }
}