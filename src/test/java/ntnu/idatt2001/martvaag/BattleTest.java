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

    @Test
    @DisplayName("Simulate a battle")
    void simulateBattleBetweenTwoArmies() {

        ArrayList<Unit> units1 = new ArrayList<>();
        ArrayList<Unit> units2 = new ArrayList<>();

        for(int i = 0; i<500; i++){
            units1.add(new InfantryUnit("Footman", 100));
            units2.add(new InfantryUnit("Grunt", 100));
        }
        for(int i = 0; i<100; i++){
            units1.add(new CavalryUnit("Knight", 100));
            units2.add(new CavalryUnit("Raider", 100));
        }
        for(int i = 0; i<200; i++){
            units1.add(new RangedUnit("Archer", 100));
            units2.add(new RangedUnit("Spearman", 100));
        }
        for(int i = 0; i<1; i++){
            units1.add(new CommanderUnit("Mountain King", 180));
            units2.add(new CommanderUnit("Gul´dan", 180));
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