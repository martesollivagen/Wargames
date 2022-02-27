package ntnu.idatt2001;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void attack() {
        Unit unit1 = new InfantryUnit("Footman", 100);
        Unit unit2 = new RangedUnit("Archer", 100);
        unit1.attack(unit2); //after this attack, unit2´s health will be 97
        assertTrue(unit2.getHealth() == 97);
    }

    @Test
    void setHealth() {
        Unit unit = new InfantryUnit("Footman", 100);
        unit.setHealth(80);
        assertTrue(unit.getHealth() == 80);
    }
}