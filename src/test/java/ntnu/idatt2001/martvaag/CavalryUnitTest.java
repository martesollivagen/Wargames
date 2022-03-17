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
        assertEquals(6,unit.getAttackBonus());
    }

    @Test
    void getAttackBonusFirstAttackFalse(){
        Unit unit = new CavalryUnit("Knight", 100);
        assertNotEquals(2,unit.getAttackBonus());
    }

    /**
     * test to check right AttackBonus-value for second attack
     */
    @Test
    void getAttackBonusSecondAttack() {
        Unit unit1 = new CavalryUnit("Knight", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit1.attack(unit2);
        assertEquals(2,unit1.getAttackBonus());
    }

    @Test
    void getAttackBonusSecondAttackFalse(){
        Unit unit1 = new CavalryUnit("Knight", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit1.attack(unit2);
        assertNotEquals(6,unit1.getAttackBonus());
    }
}