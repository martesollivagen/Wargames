package ntnu.idatt2001.martvaag.battle;

import ntnu.idatt2001.martvaag.tools.enums.Terrain;
import ntnu.idatt2001.martvaag.tools.factory.UnitFactory;
import ntnu.idatt2001.martvaag.tools.enums.UnitTypes;
import ntnu.idatt2001.martvaag.unit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * class containing test for Battle class
 * @version 2022-05-19
 * @author martvaag
 */
public class BattleTest {

    private final static Terrain FOREST = Terrain.FOREST;

    @Test
    @DisplayName("Simulate a battle")
    public void simulateBattleBetweenTwoArmies() {

        ArrayList<Unit> unitsHumanArmy = new ArrayList<>();

        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(500,UnitTypes.INFANTRYUNIT,"Footman",100));
        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(100,UnitTypes.CAVALRYUNIT,"Knight",100));
        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(200,UnitTypes.RANGEDUNIT,"Archer",100));
        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(1,UnitTypes.COMMANDERUNIT,"Mountain King",100));

        ArrayList<Unit> unitsOrcishHorde = new ArrayList<>();

        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(500,UnitTypes.INFANTRYUNIT,"Grunt",100));
        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(100,UnitTypes.CAVALRYUNIT,"Raider",100));
        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(200,UnitTypes.RANGEDUNIT,"Spearman",100));
        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(1,UnitTypes.COMMANDERUNIT,"Gul´dan",100));

        Army HumanArmy = new Army("Human Army", unitsHumanArmy);
        Army OrcishHorde = new Army("Orcish Horde", unitsOrcishHorde);

        Battle battle = new Battle(HumanArmy, OrcishHorde);
        if (battle.simulate(FOREST).equals(HumanArmy)){
            assertTrue(HumanArmy.hasUnits());
            assertFalse(OrcishHorde.hasUnits());
        } else {
            assertTrue(OrcishHorde.hasUnits());
            assertFalse(HumanArmy.hasUnits());
        }
    }

    @Test
    @DisplayName("Try create battle with null armies")
    public void createBattleWithNullArmiesToCheckThrownException(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Battle battle = new Battle(null, null);
        }, "The armies cannot be null");
        assertEquals("The armies cannot be null", thrown.getMessage());
    }

    @Test
    @DisplayName("Try create battle with invalid terrain")
    public void createBattleWithInvalidTerrainToCheckThrownException(){
        ArrayList<Unit> unitsHumanArmy = new ArrayList<>();

        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(500,UnitTypes.INFANTRYUNIT,"Footman",100));
        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(100,UnitTypes.CAVALRYUNIT,"Knight",100));
        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(200, UnitTypes.RANGEDUNIT,"Archer",100));
        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(1,UnitTypes.COMMANDERUNIT,"Mountain King",100));

        ArrayList<Unit> unitsOrcishHorde = new ArrayList<>();

        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(500,UnitTypes.INFANTRYUNIT,"Grunt",100));
        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(100,UnitTypes.CAVALRYUNIT,"Raider",100));
        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(200,UnitTypes.RANGEDUNIT,"Spearman",100));
        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(1,UnitTypes.COMMANDERUNIT,"Gul´dan",100));

        Army HumanArmy = new Army("Human Army", unitsHumanArmy);
        Army OrcishHorde = new Army("Orcish Horde", unitsOrcishHorde);
        Battle battle = new Battle(HumanArmy, OrcishHorde);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            battle.simulate(Terrain.valueOf("Ground"));
        }, "Invalid terrain");
        assertEquals("No enum constant ntnu.idatt2001.martvaag.tools.enums.Terrain.Ground", thrown.getMessage());
    }
}