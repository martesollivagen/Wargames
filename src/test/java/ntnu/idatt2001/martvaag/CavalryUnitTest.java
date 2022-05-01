package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.CavalryUnit;
import ntnu.idatt2001.martvaag.Unit.InfantryUnit;
import ntnu.idatt2001.martvaag.Unit.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for CavalryUnit class
 * @version 2022-04-03
 * @author martvaag
 */
class CavalryUnitTest {

    @Test
    @DisplayName("Attack bonus first attack, check correct value equals true")
    void getAttackBonusFirstAttack() {
        Unit unit = new CavalryUnit("Knight", 100);
        assertEquals(6,unit.getAttackBonus("FOREST"));
    }

    @Test
    @DisplayName("Attack bonus first attack, check wrong value equals false")
    void getAttackBonusFirstAttackFalse(){
        Unit unit = new CavalryUnit("Knight", 100);
        assertNotEquals(2,unit.getAttackBonus("FOREST"));
    }

    @Test
    @DisplayName("Attack bonus second attack, check correct value equals true")
    void getAttackBonusSecondAttack() {
        Unit unit1 = new CavalryUnit("Knight", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit1.attack(unit2,"");
        assertEquals(2,unit1.getAttackBonus("FOREST"));
    }

    @Test
    @DisplayName("Attack bonus second attack, check wrong value equals false")
    void getAttackBonusSecondAttackFalse(){
        Unit unit1 = new CavalryUnit("Knight", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit1.attack(unit2,"");
        assertNotEquals(6,unit1.getAttackBonus("FOREST"));
    }
}