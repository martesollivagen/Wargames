package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * class containing test for Battle class
 * @version 2022-04-03
 * @author martvaag
 */
class BattleTest {

    private final static String FOREST = "FOREST";

    @Test
    @DisplayName("Simulate a battle")
    void simulateBattleBetweenTwoArmies() {

        ArrayList<Unit> unitsHumanArmy = new ArrayList<>();

        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(500,"infantryunit","Footman",100));
        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(100,"cavalryunit","Knight",100));
        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(200,"rangedunit","Archer",100));
        unitsHumanArmy.addAll(UnitFactory.createMultipleUnits(1,"commanderunit","Mountain King",100));

        ArrayList<Unit> unitsOrcishHorde = new ArrayList<>();

        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(500,"infantryunit","Grunt",100));
        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(100,"cavalryunit","Raider",100));
        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(200,"rangedunit","Spearman",100));
        unitsOrcishHorde.addAll(UnitFactory.createMultipleUnits(1,"commanderunit","Gul´dan",100));

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
}