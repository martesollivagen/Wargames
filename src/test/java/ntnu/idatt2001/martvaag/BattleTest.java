package ntnu.idatt2001.martvaag;

import ntnu.idatt2001.martvaag.Unit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * class containing test for Battle class
 * @version 1.0 2022-03-05
 * @author martvaag
 */
class BattleTest {

    @Test
    @DisplayName("Simulate a battle")
    void simulateBattleBetweenTwoArmies() {
        Unit unit11 = new InfantryUnit("Footman", 100);
        Unit unit12 = new CavalryUnit("Knight", 100);
        Unit unit13 = new RangedUnit("Archer", 100);
        Unit unit14 = new CommanderUnit("Mountain King", 180);

        Unit unit21 = new InfantryUnit("Grunt", 100);
        Unit unit22 = new CavalryUnit("Raider", 100);
        Unit unit23 = new RangedUnit("Spearman", 100);
        Unit unit24 = new CommanderUnit("Gul´dan", 180);

        ArrayList<Unit> units1 = new ArrayList<>();
        ArrayList<Unit> units2 = new ArrayList<>();

        for(int i = 0; i<500; i++){
            units1.add(unit11);
            units2.add(unit21);
        }
        for(int i = 0; i<100; i++){
            units1.add(unit12);
            units2.add(unit22);
        }
        for(int i = 0; i<200; i++){
            units1.add(unit13);
            units2.add(unit23);
        }
        for(int i = 0; i<1; i++){
            units1.add(unit14);
            units2.add(unit24);
        }

        Army HumanArmy = new Army("Human Army", units1);
        Army OrcishHorde = new Army("Orcish Horde", units2);

        Battle battle = new Battle(HumanArmy, OrcishHorde);
        if (battle.simulate().equals(HumanArmy)){
            assertTrue(HumanArmy.hasUnits());
            assertFalse(OrcishHorde.hasUnits());
        } else {
            assertTrue(OrcishHorde.hasUnits());
            assertFalse(HumanArmy.hasUnits());
        }
    }
}