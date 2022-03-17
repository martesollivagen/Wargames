package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.InfantryUnit;
import ntnu.idatt2001.martvaag.Unit.RangedUnit;
import ntnu.idatt2001.martvaag.Unit.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for Unit class
 * @version 1.0 2022-03-05
 * @author martvaag
 */
class UnitTest {

    /**
     * test to check attack-method
     * after this attack, the health of unit2 will be 97
     */
    @Test
    void attack() {
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        unit1.attack(unit2);
        //assertTrue(unit2.getHealth() == 97);
        assertEquals(97, unit2.getHealth());
    }

    @Test
    void attackFalse(){
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        unit1.attack(unit2);
        assertNotEquals(100, unit2.getHealth());
    }

    /**
     * test to check setHealth-method
     */
    @Test
    void setHealthTo80IsTrue() {
        Unit unit = new InfantryUnit("Footman", 100);
        unit.setHealth(80);
        assertEquals(80, unit.getHealth());
    }

    @Test
    void setHealthTo80IsFalse(){
        Unit unit = new InfantryUnit("Footman", 100);
        unit.setHealth(80);
        assertNotEquals(100, unit.getHealth());
    }
}