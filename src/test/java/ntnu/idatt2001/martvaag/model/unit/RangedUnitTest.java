package ntnu.idatt2001.martvaag.model.unit;

import ntnu.idatt2001.martvaag.model.tools.enums.Terrain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class containing tests for RangedUnit class
 * @version 2022-05-19
 * @author martvaag
 */
public class RangedUnitTest {

    @Test
    @DisplayName("Resist bonus first resist, check correct value equals true")
    public void getResistBonusRangedUnitForFirstResistCheckCorrectValue() {
        Unit unit1 = new RangedUnit("Archer", 100);
        assertEquals(6,unit1.getResistBonus(Terrain.PLAINS));
    }

    @Test
    @DisplayName("Resist bonus first attack, check wrong value equals false")
    public void getResistBonusRangedUnitForFirstResistCheckForFalseValue(){
        Unit unit1 = new RangedUnit("Archer", 100);
        assertNotEquals(4,unit1.getResistBonus(Terrain.PLAINS));
    }

    @Test
    @DisplayName("Resist bonus second resist, check correct value equals true")
    public void getResistBonusRangedUnitForSecondResistCheckCorrectValue() {
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1,Terrain.PLAINS);
        assertEquals(4,unit1.getResistBonus(Terrain.PLAINS));
    }

    @Test
    @DisplayName("Resist bonus second resist, check wrong value equals false")
    public void getResistBonusRangedUnitForSecondResistCheckForFalseValue(){
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1,Terrain.PLAINS);
        assertNotEquals(6,unit1.getResistBonus(Terrain.PLAINS));
    }

    @Test
    @DisplayName("Resist bonus third resist, check correct value equals true")
    public void getResistBonusRangedUnitForThirdResistCheckCorrectValue() {
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1,Terrain.PLAINS);
        unit2.attack(unit1,Terrain.PLAINS);
        assertEquals(2,unit1.getResistBonus(Terrain.PLAINS));
    }

    @Test
    @DisplayName("Resist bonus third resist, check wrong value equals false")
    public void getResistBonusRangedUnitForThirdResistCheckForFalseValue(){
        Unit unit1 = new RangedUnit("Archer", 100);
        Unit unit2 = new InfantryUnit("Footman", 100);
        unit2.attack(unit1, Terrain.PLAINS);
        unit2.attack(unit1,Terrain.PLAINS);
        assertNotEquals(4,unit1.getResistBonus(Terrain.PLAINS));
    }
}