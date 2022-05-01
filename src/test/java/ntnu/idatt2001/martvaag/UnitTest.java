package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.unit.InfantryUnit;
import ntnu.idatt2001.martvaag.unit.RangedUnit;
import ntnu.idatt2001.martvaag.unit.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for Unit class
 * @version 2022-04-03
 * @author martvaag
 */
class UnitTest {

    @Test
    @DisplayName("Unit attack another unit, check correct value equals true")
    void unitAttackAnotherUnit() {
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        unit1.attack(unit2,"HILL");
        assertEquals(97, unit2.getHealth());
    }

    @Test
    @DisplayName("Unit attack another unit, check wrong value equals false")
    void unitAttackAnotherUnitFalse(){
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        unit1.attack(unit2,"");
        assertNotEquals(100, unit2.getHealth());
    }

    @Test
    @DisplayName("Set unit health, check correct value equals true")
    void setHealthTo80IsTrue() {
        Unit unit = new InfantryUnit("Footman", 100);
        unit.setHealth(80);
        assertEquals(80, unit.getHealth());
    }

    @Test
    @DisplayName("Set unit health, check wrong value equals false")
    void setHealthTo80IsFalse(){
        Unit unit = new InfantryUnit("Footman", 100);
        unit.setHealth(80);
        assertNotEquals(100, unit.getHealth());
    }

    @Test
    @DisplayName("Try create unit with invalid health")
    void createUnitWithInvalidHealth(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Unit unit = new InfantryUnit("Footman",-100);
        }, "Unit's health cannot be below 0");
        assertEquals("Unit's health cannot be below 0", thrown.getMessage());
    }

    @Test
    @DisplayName("Try create unit with empty name")
    void createUnitWithEmptyName(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Unit unit = new InfantryUnit("",100);
        }, "Unit's name cannot be empty");
        assertEquals("Unit's name cannot be empty", thrown.getMessage());
    }
}