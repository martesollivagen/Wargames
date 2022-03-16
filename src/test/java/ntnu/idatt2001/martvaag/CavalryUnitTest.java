package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.CavalryUnit;
import ntnu.idatt2001.martvaag.Unit.InfantryUnit;
import ntnu.idatt2001.martvaag.Unit.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for CavalryUnit class
 * @version 1.0 2022-03-05
 * @author martvaag
 */
class CavalryUnitTest {

    /**
     * test to check right AttackBonus-value for first attack
     */
    @Test
    void getAttackBonusFirstAttack() {
        Unit unit = new CavalryUnit("Knight", 100);
        assertTrue(unit.getAttackBonus() == 6);
    }

    /**
     * test to check right AttackBonus-value for second attack
     */
    @Test
    void getAttackBonusSecondAttack() {
        Unit unit1 = new CavalryUnit("Knight", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit1.attack(unit2);
        assertTrue(unit1.getAttackBonus() == 2);
    }
}