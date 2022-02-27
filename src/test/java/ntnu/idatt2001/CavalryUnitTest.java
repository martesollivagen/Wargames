package ntnu.idatt2001;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {

    @Test
    void getAttackBonusFirstAttack() {
        Unit unit = new CavalryUnit("Knight", 100);
        assertTrue(unit.getAttackBonus() == 6);
    }

    @Test
    void getAttackBonusSecondAttack() {
        Unit unit1 = new CavalryUnit("Knight", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit1.attack(unit2);
        assertTrue(unit1.getAttackBonus() == 2);
    }
}