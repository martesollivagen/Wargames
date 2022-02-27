package ntnu.idatt2001;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {

    @Test
    void getResistBonusFirstResist() {
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        assertTrue(unit1.getResistBonus() == 6);
    }

    @Test
    void getResistBonusSecondResist() {
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1);
        assertTrue(unit1.getResistBonus() == 4);
    }

    @Test
    void getResistBonusThirdResist() {
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1);
        unit2.attack(unit1);
        assertTrue(unit1.getResistBonus() == 2);
    }
}