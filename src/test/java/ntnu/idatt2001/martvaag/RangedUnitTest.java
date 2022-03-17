package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.InfantryUnit;
import ntnu.idatt2001.martvaag.Unit.RangedUnit;
import ntnu.idatt2001.martvaag.Unit.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for RangedUnit class
 * @version 1.0 2022-03-05
 * @author martvaag
 */
class RangedUnitTest {

    /**
     * test to check right ResistBonus-value for first attack
     */
    @Test
    void getResistBonusFirstResist() {
        Unit unit1 = new RangedUnit("Archer", 100);
        assertEquals(6,unit1.getResistBonus());
    }

    @Test
    void getResistBonusFirstResistFalse(){
        Unit unit1 = new RangedUnit("Archer", 100);
        assertNotEquals(4,unit1.getResistBonus());
    }

    /**
     * test to check right ResistBonus-value for second attack
     */
    @Test
    void getResistBonusSecondResist() {
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1);
        assertEquals(4,unit1.getResistBonus());
    }

    @Test
    void getResistBonusSecondResistFalse(){
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1);
        assertNotEquals(6,unit1.getResistBonus());
    }

    /**
     * test to check right ResistBonus-value for third attack
     */
    @Test
    void getResistBonusThirdResist() {
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1);
        unit2.attack(unit1);
        assertEquals(2,unit1.getResistBonus());
    }

    @Test
    void getResistBonusThirdResistFalse(){
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1);
        unit2.attack(unit1);
        assertNotEquals(4,unit1.getResistBonus());
    }
}