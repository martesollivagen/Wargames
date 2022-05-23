package ntnu.idatt2001.martvaag.model.unit;

import ntnu.idatt2001.martvaag.model.tools.enums.Terrain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for CavalryUnit class
 * @version 2022-05-19
 * @author martvaag
 */
public class CavalryUnitTest {

    @Test
    @DisplayName("Attack bonus first attack, check correct value equals true")
    public void getAttackBonusForFirstAttackCheckCorrectValue() {
        Unit unit = new CavalryUnit("Knight", 100);
        assertEquals(6,unit.getAttackBonus(Terrain.FOREST));
    }

    @Test
    @DisplayName("Attack bonus first attack, check wrong value equals false")
    public void getAttackBonusForFirstAttackCheckFalseValue(){
        Unit unit = new CavalryUnit("Knight", 100);
        assertNotEquals(2,unit.getAttackBonus(Terrain.FOREST));
    }

    @Test
    @DisplayName("Attack bonus second attack, check correct value equals true")
    public void getAttackBonusForSecondAttackCheckCorrectValue() {
        Unit unit1 = new CavalryUnit("Knight", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit1.attack(unit2,Terrain.FOREST);
        assertEquals(2,unit1.getAttackBonus(Terrain.FOREST));
    }

    @Test
    @DisplayName("Attack bonus second attack, check wrong value equals false")
    public void getAttackBonusForSecondAttackCheckFalseValue(){
        Unit unit1 = new CavalryUnit("Knight", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit1.attack(unit2, Terrain.FOREST);
        assertNotEquals(6,unit1.getAttackBonus(Terrain.FOREST));
    }
}