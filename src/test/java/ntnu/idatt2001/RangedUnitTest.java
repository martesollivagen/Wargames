package ntnu.idatt2001.martvaag;

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
        Unit unit2 = new InfantryUnit("Footman", 100);
        assertTrue(unit1.getResistBonus() == 6);
    }

    /**
     * test to check right ResistBonus-value for second attack
     */
    @Test
    void getResistBonusSecondResist() {
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1);
        assertTrue(unit1.getResistBonus() == 4);
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
        assertTrue(unit1.getResistBonus() == 2);
    }
}